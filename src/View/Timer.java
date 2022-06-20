package View;


import javafx.concurrent.Task;
import javafx.scene.control.Label;



public class Timer {
    private static Thread timerThread;
    public void runTimer(Label timeSpent) {
        Task task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                for (int second = 0; ; second++) {
                    if (isCancelled()) {
                        break;
                    }
                    Thread.sleep(1000);
                    this.updateMessage(second + "");
                }
                return null;
            }
        };

        timeSpent.textProperty().bind(task.messageProperty());
        timerThread = new Thread(task);
        timerThread.setDaemon(true);
        timerThread.start();
    }

    public void stopTimer() {
        timerThread.interrupt();
    }
}
