package com.xc.demo.scannersdkdemo.pages;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.xc.demo.scannersdkdemo.BaseFragment;
import com.xc.demo.scannersdkdemo.R;
import com.xcheng.scanner.BarcodeType;
import com.xcheng.scanner.XCBarcodeTag;
import com.xcheng.scanner.XcBarcodeScanner;

public class BarcodeOptionsFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener, TextView.OnEditorActionListener {

    private static final String TAG = "BarcodeOptionsFragment";

    // QRCode
    private Switch mSwQRCodeEnable;

    // Code39
    private Spinner mSpCode39CheckDigit;
    private Switch mSwTransmitChar, mSwFullAsc, mSwBase32;
    private EditText mEtCode39MaxLength, mEtCode39MinLength;

    // DataMatrix
    private Switch mSwWithSeparators;
    private EditText mEtOutputMaxLength;

    // EAN8
    private Switch mSwEan8Checksum, mSwEan8Digit2, mSwEan8Digit5, mSwEan8Required, mSwEan8Separator;

    // EAN13
    private Switch mSwCheckSum, mSw2Addon, mSw5Addon, mSwRequiredAddon, mSwSeparatorAddon;

    // Matrix25
    private Spinner mCheckDigitSpinner;

    // UPC-A
    private Switch mSwUpcCheckSum, mSwUpcNumberSystemCheck, mSwUpc2Addon, mSwUpc5Addon, mSwUpcRequiredAddon, mSwUpcSeparatorAddon, mSwUpcCountryCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_barcode_options, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View view) {
        // QRCode
        mSwQRCodeEnable = (Switch) view.findViewById(R.id.sw_qrcode_enable);
        mSwQRCodeEnable.setOnCheckedChangeListener(this);

        // Check if QRCode is enabled by default
        boolean isQRCodeEnable = XcBarcodeScanner.isBarcodeTypeEnabled(BarcodeType.QRCODE);
        mSwQRCodeEnable.setChecked(isQRCodeEnable);


        // Code 39
        mSpCode39CheckDigit = view.findViewById(R.id.sp_code39_check_digit);
        int code39CheckDigitDef = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_CODE39_CHECK_DIGIT_MODE);
        mSpCode39CheckDigit.setSelection(code39CheckDigitDef);
        mSpCode39CheckDigit.setOnItemSelectedListener(this);

        mSwTransmitChar = view.findViewById(R.id.sw_transmit_char);
        mSwTransmitChar.setOnCheckedChangeListener(this);
        int code39CheckSumDef = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_CODE39_START_STOP_TRANSMIT);
        mSwTransmitChar.setChecked(code39CheckSumDef == 1);

        mSwFullAsc = view.findViewById(R.id.sw_full_asc);
        mSwFullAsc.setOnCheckedChangeListener(this);
        int fullAsc = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_CODE39_FULL_ASCII_ENABLED);
        mSwFullAsc.setChecked(fullAsc == 1);

        mSwBase32 = view.findViewById(R.id.sw_base32);
        mSwBase32.setOnCheckedChangeListener(this);
        int base32 = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_CODE39_BASE32_ENABLED);
        mSwBase32.setChecked(base32 == 1);

        mEtCode39MaxLength = view.findViewById(R.id.et_code39_max_length);
        int code39MaxLengthDef = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_CODE39_MAX_LENGTH);
        mEtCode39MaxLength.setText(String.valueOf(code39MaxLengthDef));
        mEtCode39MaxLength.setOnEditorActionListener(this);

        mEtCode39MinLength = view.findViewById(R.id.et_code39_min_length);
        int code39MinLengthDef = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_CODE39_MIN_LENGTH);
        mEtCode39MinLength.setText(String.valueOf(code39MinLengthDef));
        mEtCode39MinLength.setOnEditorActionListener(this);


        // DataMatrix
        mSwWithSeparators = view.findViewById(R.id.sw_with_separators);
        mSwWithSeparators.setOnCheckedChangeListener(this);
        int withSeparators = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_DATAMATRIX_SEPARATOR_ENABLED);
        mSwWithSeparators.setChecked(withSeparators == 1);

        mEtOutputMaxLength = view.findViewById(R.id.et_output_max_length);
        int outputMaxLength = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_DATAMATRIX_OUTPUT_MAX_LENGTH);
        mEtOutputMaxLength.setText(String.valueOf(outputMaxLength));
        mEtOutputMaxLength.setOnEditorActionListener(this);


        // EAN8
        mSwEan8Checksum = view.findViewById(R.id.sw_ean8_checksum);
        mSwEan8Checksum.setOnCheckedChangeListener(this);
        int ean8Checksum = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_EAN8_CHECK_DIGIT_TRANSMIT);
        mSwEan8Checksum.setChecked(ean8Checksum == 1);

        mSwEan8Digit2 = view.findViewById(R.id.sw_ean8_digit_2);
        mSwEan8Digit2.setOnCheckedChangeListener(this);
        int ean8Digit2 = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_EAN8_2CHAR_ADDENDA_ENABLED);
        mSwEan8Digit2.setChecked(ean8Digit2 == 1);

        mSwEan8Digit5 = view.findViewById(R.id.sw_ean8_digit_5);
        mSwEan8Digit5.setOnCheckedChangeListener(this);
        int ean8Digit5 = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_EAN8_5CHAR_ADDENDA_ENABLED);
        mSwEan8Digit5.setChecked(ean8Digit5 == 1);

        mSwEan8Required = view.findViewById(R.id.sw_ean8_required);
        mSwEan8Required.setOnCheckedChangeListener(this);
        int ean8Required = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_EAN8_ADDENDA_REQUIRED);
        mSwEan8Required.setChecked(ean8Required == 1);

        mSwEan8Separator = view.findViewById(R.id.sw_ean8_separator);
        mSwEan8Separator.setOnCheckedChangeListener(this);
        int ean8Separator = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_EAN8_ADDENDA_SEPARATOR);
        mSwEan8Separator.setChecked(ean8Separator == 1);


        // EAN13
        mSwCheckSum = view.findViewById(R.id.sw_checksum);
        mSwCheckSum.setOnCheckedChangeListener(this);
        int checkSumDef = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_EAN13_CHECK_DIGIT_TRANSMIT);
        mSwCheckSum.setChecked(checkSumDef == 1);

        mSw2Addon = view.findViewById(R.id.sw_2addon);
        mSw2Addon.setOnCheckedChangeListener(this);
        int twoAddonDef = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_EAN13_2CHAR_ADDENDA_ENABLED);
        mSw2Addon.setChecked(twoAddonDef == 1);

        mSw5Addon = view.findViewById(R.id.sw_5addon);
        mSw5Addon.setOnCheckedChangeListener(this);
        int fiveAddonDef = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_EAN13_5CHAR_ADDENDA_ENABLED);
        mSw5Addon.setChecked(fiveAddonDef == 1);

        mSwRequiredAddon = view.findViewById(R.id.sw_required_addon);
        mSwRequiredAddon.setOnCheckedChangeListener(this);
        int requiredAddonDef = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_EAN13_ADDENDA_REQUIRED);
        mSwRequiredAddon.setChecked(requiredAddonDef == 1);

        mSwSeparatorAddon = view.findViewById(R.id.sw_separator_addon);
        mSwSeparatorAddon.setOnCheckedChangeListener(this);
        int separatorAddonDef = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_EAN13_ADDENDA_SEPARATOR);
        mSwSeparatorAddon.setChecked(separatorAddonDef == 1);


        // Matrix25
        mCheckDigitSpinner = view.findViewById(R.id.sp_check_digit);
        int checkDigitDef = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_M25_CHECK_DIGIT_MODE);
        mCheckDigitSpinner.setSelection(checkDigitDef);
        mCheckDigitSpinner.setOnItemSelectedListener(this);


        // UPCA
        mSwUpcCheckSum = view.findViewById(R.id.sw_upc_checksum);
        mSwUpcCheckSum.setOnCheckedChangeListener(this);
        int upcCheckSumDef = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_UPCA_CHECK_DIGIT_TRANSMIT);
        mSwUpcCheckSum.setChecked(upcCheckSumDef == 1);

        mSwUpcNumberSystemCheck = view.findViewById(R.id.sw_upc_number_check);
        mSwUpcNumberSystemCheck.setOnCheckedChangeListener(this);
        int numberSystemDef = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_UPCA_NUMBER_SYSTEM_TRANSMIT);
        mSwUpcNumberSystemCheck.setChecked(numberSystemDef == 1);

        mSwUpc2Addon = view.findViewById(R.id.sw_upc_2addon);
        mSwUpc2Addon.setOnCheckedChangeListener(this);
        int upcTwoAddonDef = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_UPCA_2CHAR_ADDENDA_ENABLED);
        mSwUpc2Addon.setChecked(upcTwoAddonDef == 1);

        mSwUpc5Addon = view.findViewById(R.id.sw_upc_5addon);
        mSwUpc5Addon.setOnCheckedChangeListener(this);
        int upcFiveAddonDef = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_UPCA_5CHAR_ADDENDA_ENABLED);
        mSwUpc5Addon.setChecked(upcFiveAddonDef == 1);

        mSwUpcRequiredAddon = view.findViewById(R.id.sw_upc_required_addon);
        mSwUpcRequiredAddon.setOnCheckedChangeListener(this);
        int upcRequiredAddonDef = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_UPCA_ADDENDA_REQUIRED);
        mSwUpcRequiredAddon.setChecked(upcRequiredAddonDef == 1);

        mSwUpcSeparatorAddon = view.findViewById(R.id.sw_upc_separator_addon);
        mSwUpcSeparatorAddon.setOnCheckedChangeListener(this);
        int upcSeparatorAddonDef = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_UPCA_ADDENDA_SEPARATOR);
        mSwUpcSeparatorAddon.setChecked(upcSeparatorAddonDef == 1);

        mSwUpcCountryCode = view.findViewById(R.id.sw_upc_country_code);
        mSwUpcCountryCode.setOnCheckedChangeListener(this);
        int countryCodeDef = XcBarcodeScanner.getDecoderTagValue(XCBarcodeTag.TAG_UPCA_ADD_COUNTRY_CODE);
        mSwUpcCountryCode.setChecked(countryCodeDef == 1);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.sw_qrcode_enable) {
            // Enable/disable QRCode
            XcBarcodeScanner.enableBarcodeType(BarcodeType.QRCODE, isChecked);
        } else if (buttonView.getId() == R.id.sw_checksum) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_EAN13_CHECK_DIGIT_TRANSMIT, isChecked ? 1 : 0);
        } else if (buttonView.getId() == R.id.sw_2addon) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_EAN13_2CHAR_ADDENDA_ENABLED, isChecked ? 1 : 0);
        } else if (buttonView.getId() == R.id.sw_5addon) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_EAN13_5CHAR_ADDENDA_ENABLED, isChecked ? 1 : 0);
        } else if (buttonView.getId() == R.id.sw_required_addon) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_EAN13_ADDENDA_REQUIRED, isChecked ? 1 : 0);
        } else if (buttonView.getId() == R.id.sw_separator_addon) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_EAN13_ADDENDA_SEPARATOR, isChecked ? 1 : 0);
        } else if (buttonView.getId() == R.id.sw_upc_checksum) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_UPCA_CHECK_DIGIT_TRANSMIT, isChecked ? 1 : 0);
        } else if (buttonView.getId() == R.id.sw_upc_number_check) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_UPCA_NUMBER_SYSTEM_TRANSMIT, isChecked ? 1 : 0);
        } else if (buttonView.getId() == R.id.sw_upc_2addon) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_UPCA_2CHAR_ADDENDA_ENABLED, isChecked ? 1 : 0);
        } else if (buttonView.getId() == R.id.sw_upc_5addon) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_UPCA_5CHAR_ADDENDA_ENABLED, isChecked ? 1 : 0);
        } else if (buttonView.getId() == R.id.sw_upc_required_addon) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_UPCA_ADDENDA_REQUIRED, isChecked ? 1 : 0);
        } else if (buttonView.getId() == R.id.sw_upc_separator_addon) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_UPCA_ADDENDA_SEPARATOR, isChecked ? 1 : 0);
        } else if (buttonView.getId() == R.id.sw_upc_country_code) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_UPCA_ADD_COUNTRY_CODE, isChecked ? 1 : 0);
        } else if (buttonView.getId() == R.id.sw_transmit_char) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_CODE39_START_STOP_TRANSMIT, isChecked ? 1 : 0);
        } else if (buttonView.getId() == R.id.sw_full_asc) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_CODE39_FULL_ASCII_ENABLED, isChecked ? 1 : 0);
        } else if (buttonView.getId() == R.id.sw_base32) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_CODE39_BASE32_ENABLED, isChecked ? 1 : 0);
        } else if (buttonView.getId() == R.id.sw_with_separators) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_DATAMATRIX_SEPARATOR_ENABLED, isChecked ? 1 : 0);
        } else if (buttonView.getId() == R.id.sw_ean8_checksum) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_EAN8_CHECK_DIGIT_TRANSMIT, isChecked ? 1 : 0);
        } else if (buttonView.getId() == R.id.sw_ean8_digit_2) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_EAN8_2CHAR_ADDENDA_ENABLED, isChecked ? 1 : 0);
        } else if (buttonView.getId() == R.id.sw_ean8_digit_5) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_EAN8_5CHAR_ADDENDA_ENABLED, isChecked ? 1 : 0);
        } else if (buttonView.getId() == R.id.sw_ean8_required) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_EAN8_ADDENDA_REQUIRED, isChecked ? 1 : 0);
        } else if (buttonView.getId() == R.id.sw_ean8_separator) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_EAN8_ADDENDA_SEPARATOR, isChecked ? 1 : 0);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.sp_check_digit) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_M25_CHECK_DIGIT_MODE, position);
        } else if (parent.getId() == R.id.sp_code39_check_digit) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_CODE39_CHECK_DIGIT_MODE, position);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        Log.d(TAG, "[onEditorAction] actionId = " + actionId + ", TextView = " + v.getId());
        String val = v.getText().toString().trim();
        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_PREVIOUS) {
            switch (v.getId()) {
                case R.id.et_code39_max_length:
                    XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_CODE39_MAX_LENGTH, val.isEmpty() ? 127 : Integer.parseInt(val));
                    break;
                case R.id.et_code39_min_length:
                    XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_CODE39_MIN_LENGTH, val.isEmpty() ? 1 : Integer.parseInt(val));
                    break;
                case R.id.et_output_max_length:
                    XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_DATAMATRIX_OUTPUT_MAX_LENGTH, val.isEmpty() ? 0 : Integer.parseInt(val));
                    break;
                default:
                    break;
            }
        }
        return false;
    }
}
