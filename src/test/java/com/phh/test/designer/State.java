package com.phh.test.designer;

import org.junit.Test;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/3/13
 */
public class State {

    //在状态模式中，类行为基于其状态而改变。
    //状态模式是一种行为模式。
    //当使用状态模式时，我们创建各种状态对象和上下文对象，其行为随着其状态对象改变而变化

    public interface IState {
        void action(Context ctx);
    }

    public class StartState implements IState {

        @Override
        public void action(Context ctx) {
            System.out.println("start state.......");
            ctx.setState(this);
        }
    }

    public class StopState implements IState {

        @Override
        public void action(Context ctx) {
            System.out.println("stop state.......");
            ctx.setState(this);
        }
    }

    public class Context {

        private IState state;

        public void setState(IState state) {
            this.state = state;
        }

    }

    @Test
    public void test() {

        Context context = new Context();
        StartState startState = new StartState();

        startState.action(context);

        StopState stopState=new StopState();
        stopState.action(context);


    }

}
