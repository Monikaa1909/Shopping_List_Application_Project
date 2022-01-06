package com.example.shoppinglistapplication.api;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.builder.productBuilder.SimpleProductBuilder;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

public class BookDetailsActivity extends AppCompatActivity {
    public final static String EXTRA_BOOK_OBJ = "EXTRA_BOOK_OBJ";

    private TextView bookTitleTextView;
    private TextView bookAuthorTextView;
    private ImageView bookCover;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        bookTitleTextView = findViewById(R.id.book_title);
        bookAuthorTextView = findViewById(R.id.book_author);
        bookCover = findViewById(R.id.img_cover);

        Book book = (Book) getIntent().getExtras().getSerializable(EXTRA_BOOK_OBJ);

        bookTitleTextView.setText(book.getTitle());
        bookAuthorTextView.setText(TextUtils.join(", ", book.getAuthors()));

        if (book.getCover() != null) {
            Picasso.with(getApplicationContext())
                    .load(MainApiActivity.IMAGE_URL_BASE + book.getCover() + "-L.jpg")
                    .placeholder(R.drawable.ic_baseline_menu_book_24).into(bookCover);
        } else {
            bookCover.setImageResource(R.drawable.ic_baseline_menu_book_24);
        }

    }
}