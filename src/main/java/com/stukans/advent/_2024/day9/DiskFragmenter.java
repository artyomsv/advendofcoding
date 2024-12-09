package com.stukans.advent._2024.day9;

import com.stukans.advent._2024.Puzzle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DiskFragmenter extends Puzzle {

    @Override
    public long solution1(File file) {
        String[] load = load(file);
        if (load.length > 1) {
            throw new RuntimeException("Too many loads!");
        }
        char[] charArray = load[0].toCharArray();

        List<Element> list = buildFragmentedView(charArray);
        List<Element> fragmented = fragment(list);
        long calculate = calculate(fragmented);
        return calculate;
    }

    @Override
    public long solution2(File file) {
        String[] load = load(file);
        if (load.length > 1) {
            throw new RuntimeException("Too many loads!");
        }
        char[] charArray = load[0].toCharArray();

        List<Element> list = buildFragmentedView(charArray);
        List<Element> fragmented = fragment(list);
        long calculate = calculate(fragmented);
        return calculate;
    }

    record Element(int index, int value) {
    }

    protected long calculate(List<Element> elements) {
        long answer = 0;
        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            if (element.value != -1) {
                long answer1 = i * element.value();
                answer += answer1;
            }

        }
        return answer;
    }

    protected List<Element> fragment(final List<Element> list) {
        final List<Element> fragmented = new ArrayList<>(list);

        int freeSpaceLocation = 0;
        for (int i = list.size() - 1; i >= freeSpaceLocation; i--) {
            Element c = fragmented.get(i);
            if (c.value() == -1) {
                continue;
            }
            freeSpaceLocation = getNextFreeSlot(fragmented, freeSpaceLocation);
            if (freeSpaceLocation > i) {
                break;
            }
            Element empty = fragmented.get(freeSpaceLocation);
            fragmented.set(freeSpaceLocation, c);
            fragmented.set(i, empty);
        }
        return fragmented;
    }

    private static int getNextFreeSlot(List<Element> fragmented, int freeSpaceLocation) {
        for (int i = freeSpaceLocation; i < fragmented.size(); i++) {
            if (fragmented.get(i).value() == -1) {
                return i;
            }
        }
        throw new RuntimeException("Too many fragments!");
    }

    protected List<Element> buildFragmentedView(char[] charArray) {
        List<Element> elements = new ArrayList<>(charArray.length);
        int index = 0;
        int valueIndex = 0;
        for (int i = 0; i < charArray.length; i += 2) {
            int fileBlockSpace = Character.getNumericValue(charArray[i]);
            for (int j = 0; j < fileBlockSpace; j++) {
                elements.add(new Element(index, valueIndex));
                index++;
            }
            valueIndex++;
            if (i + 1 < charArray.length) {
                int freeSpaceBlockSpace = Character.getNumericValue(charArray[i + 1]);
                for (int j = 0; j < freeSpaceBlockSpace; j++) {
                    elements.add(new Element(index, -1));
                    index++;
                }
            }

            index++;
        }
        return elements;
    }

}
