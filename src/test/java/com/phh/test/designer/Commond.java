package com.phh.test.designer;

import org.junit.Test;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/3/13
 */
public class Commond {

    public interface Cmd {
        void execute();
    }

    public class Receiver {
        public void action() {
            System.out.println("do something........");
        }
    }

    public class MyCmd implements Cmd {

        private Receiver receiver;

        public MyCmd(Receiver receiver) {
            this.receiver = receiver;
        }

        @Override
        public void execute() {
            this.receiver.action();
        }
    }

    public class Invoke {

        private Cmd cmd;

        public Invoke(Cmd cmd) {
            this.cmd = cmd;
        }

        public void action() {
            cmd.execute();
        }
    }


    @Test
    public void test() {
        Cmd cmd = new MyCmd(new Receiver());
        Invoke invoke = new Invoke(cmd);
        invoke.action();
    }

}
