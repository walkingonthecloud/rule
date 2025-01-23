package com.wsgc.rule.dto;

public class GenericTest {
    public static void main(String[] args)
    {
        for (int j=1; j<=8; j++)
        {
            for (int k=j; k>=1; k--)
            {
                System.out.print(k);
            }
            for (int k=2; k<=j; k++)
            {
                System.out.print(k);
            }
            System.out.println();
        }

    }
}
