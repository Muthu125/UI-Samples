package uiexamples.msf.com.uiandroidexamples.adapters;

/**
 * Created by muthuv on 12/20/2016.
 */
import android.content.Context;
import android.graphics.Typeface;
import android.os.Parcel;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.MetricAffectingSpan;
import android.text.style.StyleSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;

public class DecimalFormatText {


    public static void setUpSymbolRate(Context context, TextView textView,
                                       String Price, boolean nav) {

        if (Price == null || Price.equals("")) {
            textView.setText("");
            return;
        }

        if (!Price.contains(".")) {
            Price = Price + ".00";
        }

        String currencySymbol = Price.substring(0, 2);
        //MSFLog.msg("SpannableStringBuilder " + Price);
        String beforeText = rupeeFormat(Price.substring(2, Price.indexOf(".")));
        String afterText = Price.substring(Price.indexOf(".") + 1,
                Price.length());
        String dot = ".";

        if (nav)
            currencySymbol = "";

        SpannableStringBuilder span = new SpannableStringBuilder(currencySymbol + beforeText + dot + afterText
        );

        int start = 0;
        int end;
        end = nav ? 0 : 2;

        if (!nav) {
            span.setSpan(new StyleSpan(Typeface.BOLD), start, end, 0);
            span.setSpan(new AbsoluteSizeSpan(10, true), start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new SuperscriptSpan2(), start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }


        // Sub
        start = end;
        end = start + beforeText.length() + 1 + dot.length();
        span.setSpan(new StyleSpan(Typeface.BOLD), start, end, 0);
        span.setSpan(new AbsoluteSizeSpan(16, true), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        //start = end-1;
        start = nav ? end - 1 : end - 1;
        end = start + afterText.length();
        span.setSpan(new StyleSpan(Typeface.BOLD), start, end, 0);
        span.setSpan(new AbsoluteSizeSpan(10, true), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new SuperscriptSpan2(), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        textView.setIncludeFontPadding(true);
        textView.setText(span);

    }

    public static void setUpSymbolRateWithChange(Context context, TextView textView,
                                                 String Price, int beforeTextSize, boolean nav) {

        if (Price == null || Price.equals("")) {
            textView.setText("");
            return;
        }

        String[] spilit = Price.split("\\(");

        String pricevalue = spilit[0].trim();

        if (!pricevalue.contains(".")) {
            pricevalue = pricevalue + ".00";
        }

        String currencySymbol = pricevalue.substring(0, 2);
        String beforeText = rupeeFormat(pricevalue.substring(2, pricevalue.indexOf(".")));
        String afterText = pricevalue.substring(pricevalue.indexOf(".") + 1,
                pricevalue.length());
        String dot = ".";

        if (nav)
            currencySymbol = "";

        SpannableStringBuilder span = new SpannableStringBuilder(currencySymbol + beforeText + dot + afterText + " (" + spilit[1]
        );

        int start = 0;
        int end;
        end = nav ? 0 : 2;

        if (!nav) {
            span.setSpan(new StyleSpan(Typeface.BOLD), start, end, 0);
            span.setSpan(new AbsoluteSizeSpan(10, true), start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new SuperscriptSpan2(), start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }


        // Sub
        start = end;
        end = start + beforeText.length() + 1 + dot.length();
        span.setSpan(new StyleSpan(Typeface.BOLD), start, end, 0);
        span.setSpan(new AbsoluteSizeSpan(16, true), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        //start = end-1;
        start = nav ? end - 1 : end - 1;
        end = start + afterText.length();
        span.setSpan(new StyleSpan(Typeface.BOLD), start, end, 0);
        span.setSpan(new AbsoluteSizeSpan(10, true), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new SuperscriptSpan2(), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        textView.setIncludeFontPadding(true);
        textView.setText(span);

    }


    public static String rupeeFormat(String value) {
        value = value.replace(",", "");
        char lastDigit = value.charAt(value.length() - 1);
        String result = "";
        int len = value.length() - 1;
        int nDigits = 0;

        for (int i = len - 1; i >= 0; i--) {
            result = value.charAt(i) + result;
            nDigits++;
            if (((nDigits % 2) == 0) && (i > 0)) {
                result = "," + result;
            }
        }
        return (result + lastDigit);
    }


    public static void setUpSymbolRate2(Context context, TextView textView,
                                        String bidOfferValue, int subSize, int midSize, int supSize) {

        if (bidOfferValue == null || bidOfferValue.equals("")) {
            textView.setText("");
            return;
        }

        String sub = bidOfferValue.substring(0, bidOfferValue.length() - 3);
        String mid = bidOfferValue.substring(bidOfferValue.length() - 3,
                bidOfferValue.length() - 1);
        String sup = bidOfferValue.charAt(bidOfferValue.length() - 1) + "";

        SpannableStringBuilder span = new SpannableStringBuilder(sub + mid
                + sup);

        // Sub
        int start = 0;
        int end = start + sub.length();
        span.setSpan(new AbsoluteSizeSpan(subSize, true), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Mid

        start = end;
        end = start + mid.length();
        span.setSpan(new StyleSpan(Typeface.BOLD), start, end, 0);
        span.setSpan(new AbsoluteSizeSpan(15, true), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Super
        if (sup.length() > 0) {
            start = end;
            end = start + sup.length();
            span.setSpan(new SuperscriptSpan(), start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            span.setSpan(new AbsoluteSizeSpan(supSize, true), start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        Spannable WordtoSpan = new SpannableString("13.500,27");
        WordtoSpan.setSpan(new TextAppearanceSpan(null, 0, 17, null, null), WordtoSpan.length() - 2, WordtoSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        WordtoSpan.setSpan( new SuperscriptSpan2(), WordtoSpan.length()-2, WordtoSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        textView.setIncludeFontPadding(true);
        textView.setText(WordtoSpan);

    }

//    public static class SuperscriptSpan2 extends MetricAffectingSpan implements
//            ParcelableSpan {
//
//        public SuperscriptSpan2() {
//        }
//
//        public int getSpanTypeId() {
//            return TextUtils.CAP_MODE_CHARACTERS;
//        }
//
//        public int describeContents() {
//            return 0;
//        }
//
//        public void writeToParcel(Parcel dest, int flags) {
//            writeToParcel(dest, flags);
//        }
//
//        @Override
//        public void updateDrawState(TextPaint tp) {
//            tp.baselineShift += (int) (tp.ascent() / 2.00);
//        }
//
//        @Override
//        public void updateMeasureState(TextPaint tp) {
//            tp.baselineShift += (int) (tp.ascent() / 2.00);
//        }
//
//    }


    public static class SuperscriptSpan2 extends MetricAffectingSpan {
        public SuperscriptSpan2() {
        }

        public SuperscriptSpan2(Parcel src) {
        }

        public int getSpanTypeId() {
            return getSpanTypeIdInternal();
        }

        /**
         * @hide
         */
        public int getSpanTypeIdInternal() {
            return TextUtils.CAP_MODE_CHARACTERS;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            writeToParcelInternal(dest, flags);
        }

        /**
         * @hide
         */
        public void writeToParcelInternal(Parcel dest, int flags) {
        }

        @Override
        public void updateDrawState(TextPaint tp) {
            tp.baselineShift += (int) (tp.ascent() / 2);
        }

        @Override
        public void updateMeasureState(TextPaint tp) {
            tp.baselineShift += (int) (tp.ascent() / 2);
        }
    }

}
