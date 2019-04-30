package com.phh.test.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.algorithm
 * @date 2019/4/26
 */
public class LinkedTest {

    @Test
    public void test() {
        Linked linked = new Linked();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.add(4);
        linked.add(5);
        linked.print();
        System.out.println("-------------------------------------");
        linked.reverse();
        linked.print();
    }


    @Data
    static class Node {
        private Integer data;
        private Node next;
    }

    @Data
    static class Linked {
        Node head;

        void add(Integer val) {
            Node newNode = new Node();
            newNode.setData(val);
            if (head == null) {
                head = newNode;
            } else {
                newNode.next = head;
                head = newNode;
            }
        }

        void reverse() {
            Node next = head;
            Node newHead = null;
            Node pre = null;
            while (next != null) {
                Node tmp = next.next;
                next.next = pre;
                pre = next;
                if (tmp == null) {
                    newHead = next;
                }
                next = tmp;
            }
            head = newHead;
        }

        void print() {
            if (head == null) {
                return;
            }
            Node next = head;
            while (next != null) {
                System.out.println(next.data);
                next = next.next;
            }
        }
    }

}
