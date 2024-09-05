package com.xc.demo.scannersdkdemo.pages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import com.xc.demo.scannersdkdemo.BaseFragment;
import com.xc.demo.scannersdkdemo.R;
import com.xcheng.scanner.BarcodeType;
import com.xcheng.scanner.XCBarcodeTag;
import com.xcheng.scanner.XcBarcodeScanner;

public class BarcodeOptionsFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    private Switch mSwQRCodeEnable;

    private Switch mSwCheckSum, mSw2Addon, mSw5Addon, mSwRequiredAddon, mSwSeparatorAddon;

    private Spinner mCheckDigitSpinner;

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
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.sp_check_digit) {
            XcBarcodeScanner.setDecoderTag(XCBarcodeTag.TAG_M25_CHECK_DIGIT_MODE, position);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
