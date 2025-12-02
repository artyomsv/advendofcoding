package com.stukans.advent._2024.day9;

import com.stukans.advent._2024.Pair;
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

        List<Integer> list = buildFragmentedView(charArray);
        List<Integer> fragmented = fragment(list);
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

        List<Integer> list = buildFragmentedView(charArray);
        List<Integer> fragmented = fragmentN(list);
        long calculate = calculate(fragmented);
        return calculate;
    }

    record Element(int index, int value) {
        @Override
        public String toString() {
            return value + "";
        }
    }

    protected long calculate(List<Integer> elements) {
        long answer = 0;
        for (int i = 0; i < elements.size(); i++) {
            Integer element = elements.get(i);
            if (element != -1) {
                long answer1 = (long) i * element;
                answer += answer1;
            }

        }
        return answer;
    }

    protected List<Integer> fragment(final List<Integer> list) {
        final List<Integer> fragmented = new ArrayList<>(list);

        int freeSpaceLocation = 0;
        for (int i = list.size() - 1; i >= freeSpaceLocation; i--) {
            Integer c = fragmented.get(i);
            if (c == -1) {
                continue;
            }
            freeSpaceLocation = getNextFreeSlot(fragmented, freeSpaceLocation);
            if (freeSpaceLocation > i) {
                break;
            }
            Integer empty = fragmented.get(freeSpaceLocation);
            fragmented.set(freeSpaceLocation, c);
            fragmented.set(i, empty);
        }
        return fragmented;
    }

    protected List<Integer> fragmentN(final List<Integer> list) {
        final List<Integer> fragmented = new ArrayList<>(list);
        int from = fragmented.size() - 1;
        //System.out.println(fragmented);
        do {
            Pair<Integer, Integer> filesBlock = findFileBlockToMove(fragmented, from);
            int size = filesBlock.getRight() - filesBlock.getLeft() + 1;
            Pair<Integer, Integer> freeBlock = findFreeBlockToTake(fragmented, size);


            if (freeBlock != null && filesBlock.getLeft() > freeBlock.getLeft()) {
                //System.out.println(filesBlock + "|" + freeBlock);
                for (int i = freeBlock.getLeft(); i < freeBlock.getRight() + 1; i++) {
                    fragmented.set(i, fragmented.get(filesBlock.getLeft()));
                }
                for (int i = filesBlock.getLeft(); i < filesBlock.getRight() + 1; i++) {
                    fragmented.set(i, -1);
                }
            } else {
                //System.out.println(filesBlock + "|" + freeBlock + " SKIP");
            }
            //System.out.println(fragmented);
            from = filesBlock.getLeft() - 1;
        } while (from >= 0);
        return fragmented;
    }

    public Pair<Integer, Integer> findFileBlockToMove(List<Integer> fragmented, int from) {
        int location = from;
        Integer num = fragmented.get(location);
        boolean blockStarted = false;
        int blockStart = from;
        int blockLength = 0;

        int blockValue = -1;
        boolean blockValueChanged = false;

        do {
            if (num != -1) {
                if (blockValue == -1) {
                    blockValue = num;
                } else {
                    if (blockValue != num) {
                        blockValueChanged = true;
                    }
                }
            }

            if (blockStarted && (num == -1 || blockValueChanged)) {
                return Pair.of(blockStart - blockLength + 1, blockStart);
            }

            if (num != -1) {
                if (!blockStarted) {
                    blockStart = location;
                }

                blockStarted = true;
                blockLength++;
            } else {
                blockStarted = false;
                blockStart = 0;
                blockLength = 0;
            }
            location--;
            if (location < 0) {
                if (blockStarted == false) {
                    return null;
                } else {
                    return Pair.of(0, blockStart);
                }
            }
            num = fragmented.get(location);
        } while (location < fragmented.size() - 1);

        return null;
    }

    public Pair<Integer, Integer> findFreeBlockToTake(List<Integer> fragmented, int size) {
        int location = 0;
        Integer num = fragmented.get(location);
        boolean freeSpaceStarted = false;
        int freeSpaceStart = 0;
        int freeSpaceLength = 0;

        do {
            if (num == -1) {
                if (!freeSpaceStarted) {
                    freeSpaceStart = location;
                }

                freeSpaceStarted = true;
                freeSpaceLength++;

                if (freeSpaceStarted && size <= freeSpaceLength) {
                    return Pair.of(freeSpaceStart, freeSpaceStart + freeSpaceLength - 1);
                }

            } else {
                freeSpaceStarted = false;
                freeSpaceStart = 0;
                freeSpaceLength = 0;
            }
            location++;
            num = fragmented.get(location);
        } while (location < fragmented.size() - 1);

        return null;
    }

    private static List<List<Element>> mapToBlocks(List<Element> fragmented) {
        List<List<Element>> blocks = new ArrayList<>();
        List<Element> inner = new ArrayList<>();
        for (int i = 0; i < fragmented.size(); i++) {
            inner.add(fragmented.get(i));

            if (i < fragmented.size() - 1 && fragmented.get(i).value() != fragmented.get(i + 1).value()) {
                blocks.add(inner);
                inner = new ArrayList<>();
            }
        }
        blocks.add(inner);
        return blocks;
    }

    private int getNextFreeSlot(List<Integer> fragmented, int freeSpaceLocation) {
        for (int i = freeSpaceLocation; i < fragmented.size(); i++) {
            if (fragmented.get(i) == -1) {
                return i;
            }
        }
        throw new RuntimeException("Too many fragments!");
    }

    private int getNextFreeSlotN(List<List<Element>> fragmented, int size) {
        for (int i = 0; i < fragmented.size(); i++) {
            List<Element> element = fragmented.get(i);
            if (element.iterator().next().value() == -1 && size <= element.size()) {
                return i;
            }
        }
        return -1;
    }

    protected List<Integer> buildFragmentedView(char[] charArray) {
        List<Integer> elements = new ArrayList<>(charArray.length);
        int valueIndex = 0;
        for (int i = 0; i < charArray.length; i += 2) {
            int fileBlockSpace = Character.getNumericValue(charArray[i]);
            for (int j = 0; j < fileBlockSpace; j++) {
                elements.add(valueIndex);
            }
            valueIndex++;
            if (i + 1 < charArray.length) {
                int freeSpaceBlockSpace = Character.getNumericValue(charArray[i + 1]);
                for (int j = 0; j < freeSpaceBlockSpace; j++) {
                    elements.add(-1);
                }
            }
        }
        return elements;
    }

}
