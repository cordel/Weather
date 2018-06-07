package me.suzdalnitsky.weather.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import me.suzdalnitsky.weather.R;
import me.suzdalnitsky.weather.ui.main.EnterCityListener;

public class EnterCityDialogFragment extends AppCompatDialogFragment {

    public static final String FRAGMENT_TAG = "EnterCityDialogFragment";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View editTextDialogView = getEditTextDialogView();        

        Dialog dialog = new AlertDialog.Builder(getContext())
                .setView(editTextDialogView)
                .setTitle(getString(R.string.enter_city_name_dialog_title))
                .setPositiveButton(
                        getString(R.string.enter_city_name_dialog_positive),
                        (__, which) -> {
                            String text = editTextDialogView.<EditText>findViewById(R.id.edit_text_city)
                                    .getText()
                                    .toString();
                            
                            onPositive(text);
                        }
                )
                .setNegativeButton(
                        R.string.enter_city_name_dialog_negative, 
                        (__, which) -> dismiss()
                )
                .create();

        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return dialog;
    }

    private View getEditTextDialogView() {
        View view = View.inflate(getContext(), R.layout.dialog_input, null);
        EditText editText = view.findViewById(R.id.edit_text_city);
        
        editText.setOnEditorActionListener((v, actionId, event) -> {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                onPositive(editText.getText().toString());
                handled = true;
            }
            return handled;
        });
        
        return view;
    }

    private void onPositive(String text) {
        if (!text.isEmpty()) {
            ((EnterCityListener) getActivity()).onDialogProceed(text);
        }
        dismiss();
    }
}
