package com.xc.demo.scannersdkdemo.pages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.xc.demo.scannersdkdemo.BaseFragment;
import com.xc.demo.scannersdkdemo.R;
import com.xcheng.scanner.BarcodeType;
import com.xcheng.scanner.XcBarcodeScanner;

public class BarcodeOptionsFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener {

    private Switch mSwQRCodeEnable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_barcode_options, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View view) {
        mSwQRCodeEnable = (Switch) view.findViewById(R.id.sw_qrcode_enable);
        mSwQRCodeEnable.setOnCheckedChangeListener(this);

        // Check if QRCode is enabled by default
        boolean isQRCodeEnable = XcBarcodeScanner.isBarcodeTypeEnabled(BarcodeType.QRCODE);
        mSwQRCodeEnable.setChecked(isQRCodeEnable);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.sw_qrcode_enable) {
            // Enable/disable QRCode
            XcBarcodeScanner.enableBarcodeType(BarcodeType.QRCODE, isChecked);
        }
    }
}
