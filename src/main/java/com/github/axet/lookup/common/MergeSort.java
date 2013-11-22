package com.github.axet.lookup.common;

import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class MergeSort {

    public static void sort(List list, Comparator c) {
        Object[] data = list.toArray();
        data = mergeSort(data, c);
        ListIterator i = list.listIterator();
        for (Object item : data) {
            i.next();
            i.set(item);
        }
    }

    private static Object[] mergeSort(Object[] data, Comparator comparator) {
        int lenD = data.length;
        if (lenD > 1) {
            Object[] sorted;
            int middle = lenD / 2;
            int rem = lenD - middle;
            Object[] L = new Object[middle];
            Object[] R = new Object[rem];
            System.arraycopy(data, 0, L, 0, middle);
            System.arraycopy(data, middle, R, 0, rem);
            L = mergeSort(L, comparator);
            R = mergeSort(R, comparator);
            sorted = merge(L, R, comparator);

            return sorted;
        }
        return data;
    }

    private static Object[] merge(Object[] L, Object[] R, Comparator comparator) {
        int lenL = L.length;
        int lenR = R.length;
        Object[] merged = new Object[lenL + lenR];
        int i = 0;
        int j = 0;
        while (i < lenL || j < lenR) {
            if (i < lenL & j < lenR) {
                if (comparator.compare(L[i], R[j]) <= 0) {
                    merged[i + j] = L[i];
                    i++;
                } else {
                    merged[i + j] = R[j];
                    j++;
                }
            } else if (i < lenL) {
                merged[i + j] = L[i];
                i++;
            } else if (j < lenR) {
                merged[i + j] = R[j];
                j++;
            }
        }
        return merged;
    }
}
