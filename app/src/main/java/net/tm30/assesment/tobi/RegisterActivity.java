package net.tm30.assesment.tobi;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private void formatPrivacyPolicy(@ColorInt int color) {
        //? Now we configure the terms and privacy text
        TextView privacyTextView = findViewById(R.id.terms_privacy_text);
        var text = getString(R.string.accept_terms_and_privacy);
        var termsPart = "Terms and Conditions";
        var policyPart = "Privacy Policy";
        var spannableString = new SpannableString(text);

        // Declare the clickable spans.
        var termsSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Toast.makeText(RegisterActivity.this, "Terms and Conditions", Toast.LENGTH_SHORT).show();
            }

            @Override public void updateDrawState(@NonNull TextPaint painter) {
                super.updateDrawState(painter);
                painter.setColor(color);
                painter.setUnderlineText(false);
            }
        };
        var policySpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Toast.makeText(RegisterActivity.this, "Privacy Policy", Toast.LENGTH_SHORT).show();
            }

            @Override public void updateDrawState(@NonNull TextPaint painter) {
                super.updateDrawState(painter);
                painter.setColor(color);
                painter.setUnderlineText(false);
            }
        };

        // Modify the privacy policy first.
        var policyStartIndex = text.indexOf(policyPart);
        if (policyStartIndex != -1) {
            var endIndex = policyStartIndex + policyPart.length();
            spannableString.setSpan(policySpan, policyStartIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE|Spanned.SPAN_PRIORITY);
        }

        // Modify the terms and conditions to follow the privacy policy.
        var termsStartIndex = text.indexOf(termsPart);
        if (termsStartIndex != -1) {
            var endIndex = termsStartIndex + termsPart.length();
            spannableString.setSpan(termsSpan, termsStartIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE|Spanned.SPAN_PRIORITY);
        }

        //? Make actions possible.
        privacyTextView.setText(spannableString);
        privacyTextView.setLinksClickable(true);
        privacyTextView.setClickable(true);
    }

    private void formatLoginText(@ColorInt int color) {
        TextView textView = findViewById(R.id.sign_in_text);
        var text = getString(R.string.have_account);
        var clickablePart = "Sign in";
        var spannableString = new SpannableString(text);

        // Add the span
        var textSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Toast.makeText(RegisterActivity.this, "Not in scope.", Toast.LENGTH_SHORT).show();
            }

            @Override public void updateDrawState(@NonNull TextPaint painter) {
                super.updateDrawState(painter);
                painter.setColor(color);
                painter.setUnderlineText(false);
            }
        };

        // Modify the privacy policy first.
        var startIndex = text.indexOf(clickablePart);
        if (startIndex != -1) {
            var endIndex = startIndex + clickablePart.length();
            spannableString.setSpan(textSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE|Spanned.SPAN_PRIORITY);
        }


        textView.setText(spannableString);
        textView.setLinksClickable(true);
        textView.setClickable(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        var primaryColor  = ContextCompat.getColor(this, R.color.primary);

        // Initialize the toolbar
        final Toolbar toolbar = findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        //? Configure actions
        var actionBar = Objects.requireNonNull(getSupportActionBar());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        // Format the text properties
        formatPrivacyPolicy(primaryColor);
        formatLoginText(primaryColor);
    }
}
