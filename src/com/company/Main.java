package com.company;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
	    Semaphore callBox = new Semaphore(2);
        new Person("Syrym", callBox);
        new Person("Muha", callBox);
        new Person("Roma", callBox);
        new Person("Ayan", callBox);
        new Person("Kama", callBox);
    }
}
class Person extends Thread {
    String name;
    private final Semaphore callBox;
    public Person(String name, Semaphore callBox) {
        this.name = name;
        this.callBox = callBox;
        this.start();
    }
    public void run() {
        try {
            System.out.println(name + " waits for...");
            callBox.acquire();
            System.out.println(name + " uses mobilephone");
            sleep(2000);
            System.out.println(name + " has finished the call");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            callBox.release();
        }
    }
}
