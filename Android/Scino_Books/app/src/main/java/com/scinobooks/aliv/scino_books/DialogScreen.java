package com.scinobooks.aliv.scino_books;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DialogScreen {

    public static final int BOOKS = 1; // Идентификаторы диалоговых окон
    public static final int CATEGORIES = 2;


    public static AlertDialog getDialog(final Activity activity, int ID) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        switch(ID) {
            case BOOKS: // Диалоговое окно книг
                View view_book = activity.getLayoutInflater().inflate(R.layout.books, null); // Получаем layout по его ID
                builder.setView(view_book);
                builder.setTitle(R.string.book_title);
                final EditText input_name = (EditText)view_book.findViewById(R.id.input_name);
                final EditText input_author = (EditText)view_book.findViewById(R.id.input_author);
                final EditText input_title = (EditText)view_book.findViewById(R.id.input_title);
                builder.setCancelable(true);
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // Кнопка ОК
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = String.valueOf(input_name.getText());
                        String author = String.valueOf(input_author.getText());
                        String title = String.valueOf(input_title.getText());

                        if (title.length() == 0) {
                            MainActivity.mDB.addBook(new Book(name, author, "Без категории"));
                            MainActivity.mDB.addCategory(new Category("Без категории"));
                            Toast toast = Toast.makeText(MainActivity.context,
                                    "Книга добавлена в список Без категории", Toast.LENGTH_SHORT);
                            toast.show();

                        } else {
                            MainActivity.mDB.addBook(new Book(name, author, title));
                            MainActivity.mDB.addCategory(new Category(title));
                            Toast toast = Toast.makeText(MainActivity.context,
                                    "Книга добавлена в список " + title, Toast.LENGTH_SHORT);
                            toast.show();
                        }

                        //dialog.dismiss();
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // Кнопка Отмена
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                return builder.create();

            case CATEGORIES: // Диалоговое окно категорий
                View view_category = activity.getLayoutInflater().inflate(R.layout.categories, null); // Получаем layout по его ID
                builder.setView(view_category);
                builder.setTitle(R.string.category_title);
                final EditText input_title_cat = (EditText)view_category.findViewById(R.id.input_title_cat);
                builder.setCancelable(true);
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // Кнопка ОК
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String name = String.valueOf(input_title_cat.getText());

                        if (name.length() == 0) {
                            builder.setCancelable(false);
                            Toast toast = Toast.makeText(MainActivity.context,
                                    "Введите название списка!", Toast.LENGTH_SHORT);
                            toast.show();

                        } else {
                            MainActivity.mDB.addCategory(new Category(name));
                            Toast toast = Toast.makeText(MainActivity.context,
                                    "Создан список " + name, Toast.LENGTH_SHORT);
                            toast.show();
                          //  dialog.dismiss();
                        }


                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // Кнопка Отмена
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setCancelable(true);
                return builder.create();
            default:
                return null;
        }
    }
}