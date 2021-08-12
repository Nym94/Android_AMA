package com.example.ama;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Fragment3MainMenu extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment3_main_menu, container, false);

        initUI(rootView);

        return rootView;
    }

    /* Only use basic construct in fragment, because overloaded construct not guaranteed to call.
    public Fragment3MainMenu(InstalledAppNote selectedAppInfo) {
        this.selectedAppInfo = selectedAppInfo;
    }
    */

    public void initUI(ViewGroup rootView) {

        ImageView appIcon = rootView.findViewById(R.id.SelectedAppIcon2);
        TextView appName = rootView.findViewById(R.id.SelectedAppName2);
        LinearLayout layout_setPassword = rootView.findViewById(R.id.layout_set_password);
        Switch switch_setPassword = rootView.findViewById(R.id.switch_set_password);

        Bundle bundleFromFragment2 = getArguments();
        InstalledAppNote selectedAppInfo = (InstalledAppNote) bundleFromFragment2.getParcelable("selectedAppInfo");

        appIcon.setImageDrawable(selectedAppInfo.getInstalledAppIcon());
        appName.setText(selectedAppInfo.getInstalledAppName());

        layout_setPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "set password", Toast.LENGTH_LONG).show();
            }
        });

        switch_setPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switch_setPassword.isChecked() == true) {
                    Toast.makeText(getContext(), "set password", Toast.LENGTH_LONG).show();

                    // Show the dialog for set the app's password
                    DialogSetAppPassword dialogSAP = new DialogSetAppPassword();
                    dialogSAP.setOnDialogButtonClickListener(new OnSetPasswordButtonClickListener() {
                        @Override
                        public void onPositiveButtonClick() {
                            Toast.makeText(getContext(), "yeeeeesss", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNegativeButtonClick() {

                        }
                    });
                    dialogSAP.show(getActivity().getSupportFragmentManager(), "setAppPassword");
                }
                else if (switch_setPassword.isChecked() == false) {

                }
            }
        });
    }
}