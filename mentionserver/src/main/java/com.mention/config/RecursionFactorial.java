package com.mention.config;

public class RecursionFactorial {
  public static void main(String[] args) {
    System.out.println(recursion(10, 1));
  }

  public static int recursion(int fact, int state) {
    while (fact>0) {
      return state * recursion(fact - 1, state);

    }
    return state;
  }
}
