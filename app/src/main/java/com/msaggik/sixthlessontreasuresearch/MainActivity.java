package com.msaggik.sixthlessontreasuresearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    // поля
    private TextView output, field;
    private float x, y; // координаты касания TextView field

    private Box[] boxes; // массив для объектов сундуков сокровищ

    private float dimensions = 50; // габариты сундука сокровищ
    private int count = 0; // счётчик найденных сундуков

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Random random = new Random();

        Supplier<Box> supplier = () -> new Box(random.nextInt(960), random.nextInt(1450));
        boxes = Stream.generate(supplier)
                .limit(10)
                .toArray(Box[]::new);

        // привязка разметки к полям
        output = findViewById(R.id.output);
        field = findViewById(R.id.field);

        // обработка касания TextView field
        field.setOnTouchListener(listener);
    }

    // создание слушателя
    private View.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            // определение координат касания
            x = motionEvent.getX();
            y = motionEvent.getY();

            // определение типа касания
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN: // касание TextView field
                    field.setText("Касание " + x + ", " + y);
                    break;
                case MotionEvent.ACTION_MOVE: // движение по TextView field
                    field.setText("Движение " + x + ", " + y);
                    // поиск сундуков сокровищ

                    for (Box box : boxes) {
                        // если удалось провести пальцем по сундуку сокровищ и он не найден
                        if (isBoxFound(box)) {
                            box.setFound(true); // установка сундука как найденного
                            count++; // повышение счётчика поиска сундуков
                            output.setText("Найдено сундуков " + count);
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP: // отпускание TextView field
                    field.setText("Отпускание " + x + ", " + y);
                    break;
            }
            return true;
        }
    };

    private boolean isBoxFound(Box box) {
        return !box.isFound() && x >= (box.getCoordinateX() - dimensions) && x <= (box.getCoordinateX() + dimensions) &&
                y >= (box.getCoordinateY() - dimensions) && y <= (box.getCoordinateY() + dimensions);
    }
}