package aziz.data;



import aziz.util.*;
import org.jetbrains.annotations.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static aziz.data.ExchangeService.Money.EUR;
import static aziz.data.ExchangeService.Money.USD;
import static java.util.stream.Collectors.toList;

public class Shop {

    public final static List<Shop> SHOPS = Arrays.asList(newShop("BestPrice"),
            newShop("LetsSaveBig"),
            newShop("MyFavoriteShop"),
            newShop("BuyItAll"));
    public final static List<Shop> NINE_SHOPS = Arrays.asList(newShop("BestPrice"),
            newShop("LetsSaveBig"),
            newShop("MyFavoriteShop"),
            newShop("Amazing"),
            newShop("aMaze"),
            newShop("aMazatlan"),
            newShop("aMais"),
            newShop("Amazoot"),
            newShop("AMA"),
            newShop("BuyItAll"));
    private static final Random RANDOM = new Random();
    private final String name;

    public Shop(String name) {
        this.name = name;
    }

    @NotNull
    public static Shop newShop(String name) {
        return new Shop(name);
    }

    @NotNull
    public static Shop newShop() {
        return new Shop("Super");
    }

    public String getName() {
        return name;
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public String getPriceStr(String product, boolean hasRandomDelay) {
        double price;
        if (hasRandomDelay) {
            price = calculatePriceRandomDelay(product);
        } else {
            price = calculatePrice(product);
        }

        Discount.Code code = Discount.Code.values()[
                RANDOM.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, Double.valueOf(price), code);
    }

    public Future<Double> getPriceAsynch(String product) {
        final CompletableFuture futurePrice = new CompletableFuture();
        new Thread(() -> {
            double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }


    public Future<Double> getPriceAsynchException(String product) {
        final CompletableFuture futurePrice = new CompletableFuture();
        new Thread(() -> {
            try {
                double price = calculatePriceKaboom(product);
                futurePrice.complete(price);
            } catch (Exception e) {
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }

    public static List<String> findPrices(String product) {
        List<CompletableFuture<String>> priceFutures =
                SHOPS.stream()
                     .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " +
                             shop.getPrice(product)))
                     .collect(toList());
        return priceFutures.stream()
                           .map(CompletableFuture::join)
                           .collect(toList());
    }


    public static List<String> findPrices(String product, ExecutorService executor, List<Shop> shops) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                     .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " +
                                     shop.getPrice(product),
                             executor))
                     .collect(toList());
        return priceFutures.stream()
                           .map(CompletableFuture::join)
                           .collect(toList());
    }

    public static List<String> findPrices(String product, ExecutorService executor) {
        List<CompletableFuture<String>> priceFutures =
                SHOPS.stream()
                     .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " +
                                     shop.getPrice(product),
                             executor))
                     .collect(toList());
        return priceFutures.stream()
                           .map(CompletableFuture::join)
                           .collect(toList());
    }

    public static List<String> findPricesV2(String product, ExecutorService executor) {
        List<CompletableFuture<String>> priceFutures =
                SHOPS.stream()
                     .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceStr(
                             product, false), executor))
                     .map(future -> future.thenApply(Quote::parse))
                     .map(future -> future.thenCompose(quote -> CompletableFuture
                             .supplyAsync(
                                     () -> Discount.applyDiscount(quote),
                                     executor)))
                     .collect(toList());
        return priceFutures.stream()
                           .map(CompletableFuture::join)
                           .collect(toList());
    }


    /**
     * Returns the price in US $, uses supplyAsync to get the price, thenCombine to get the rate
     *
     * @param product
     * @return
     */
    public static Future<Double> futurePricesInUsd(String product) {
        return CompletableFuture.supplyAsync(() ->
                newShop().getPrice(product))
                                .thenCombine(CompletableFuture.supplyAsync(() ->
                                        ExchangeService.getRate(EUR, USD)), (price, rate) -> price * rate);
    }


    public static Stream<CompletableFuture<String>> findPricesStream(List<Shop> shops, String product, ExecutorService executor) {
        return shops.stream()
                    .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceStr(product, true), executor))
                    .map(future -> future.thenApply(Quote::parse))
                    .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote))));
    }

    public Future<Double> getPriceAsyncLambda(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }


    private double calculatePrice(String product) {
        delay();
        return RANDOM.nextDouble() * product.charAt(0) + product.charAt(1);
    }
    private double calculatePriceRandomDelay(String product) {
        Util.randomDelay();
        return RANDOM.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    private double calculatePriceKaboom(String product) {
        if (product == null) {
            throw new RuntimeException("Product should not be null");
        }

        return RANDOM.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
