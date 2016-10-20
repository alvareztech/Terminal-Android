package tech.alvarez.terminal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private TextView terminalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        terminalTextView = (TextView) findViewById(R.id.terminalTextView);

    }

    public void ping(View view) {
        System.out.println("ls pressed");
        try {
            Process process = Runtime.getRuntime().exec("ls");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
//            terminalTextView.setText("");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(">" + line);
                terminalTextView.append(line + "\n");
            }

//            DataOutputStream outputStream = new DataOutputStream(process.getOutputStream());
//
//            outputStream.writeBytes("screenrecord --time-limit 10 /sdcard/MyVideo.mp4\n");
//            outputStream.flush();
//
//            outputStream.writeBytes("exit\n");
//            outputStream.flush();
//            process.waitFor();
        } catch (IOException e) {
            System.out.println(">" + e.toString());
        }
//        } catch (InterruptedException e) {
//
//        }
    }

    public void logcat(View view) {
        System.out.println("logcat pressed");
        try {
            Process process = Runtime.getRuntime().exec("logcat");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            terminalTextView.setText("");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("<" + line + ">");
                terminalTextView.append(line + "\n");
            }
        } catch (IOException e) {
            System.out.println(">" + e.toString());
        }
    }

    public static void rebootSU() {
        Runtime runtime = Runtime.getRuntime();
        Process proc = null;
        OutputStreamWriter osw = null;
        StringBuilder sbstdOut = new StringBuilder();
        StringBuilder sbstdErr = new StringBuilder();

        String command = "/system/bin/reboot";

        try { // Run Script

            proc = runtime.exec("su");
            osw = new OutputStreamWriter(proc.getOutputStream());
            osw.write(command);
            osw.flush();
            osw.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            if (proc != null)
                proc.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        sbstdOut.append(ReadBufferedReader(new InputStreamReader(proc.getInputStream())));
//        sbstdErr.append(ReadBufferedReader(new InputStreamReader(proc.getErrorStream())));
        if (proc.exitValue() != 0) {
        }
    }
}
