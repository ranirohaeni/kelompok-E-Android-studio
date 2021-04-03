package com.example.todoliste;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.todoliste.Model.ToDoModel;
import com.example.todoliste.Utils.DataBaseHelper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class addNewTask extends BottomSheetDialogFragment {
    public static final String TAG = "AddNewTask";

//    Widget
    private EditText mEditText;
    private Button mSaveButton;
    private DataBaseHelper myDB;

    public static addNewTask newInstance(){
        return new addNewTask();
    }
//    Panggil Activity task_layout
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_newtask , container , false);
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditText = view.findViewById(R.id.edittext);
        mSaveButton = view.findViewById(R.id.buttonsave);

        myDB = new DataBaseHelper(getActivity());
        boolean isUpdate = false;

        Bundle bundle = getArguments();
        if(bundle != null){
            isUpdate = true;
            String task = bundle.getString("TASK");
            mEditText.setText(task);

            if(task.length() > 0){
                mSaveButton.setEnabled(false);
            }
        }

//         Code untuk text watcher
    mEditText.addTextChangedListener(new TextWatcher() {
//  Text watcher melakukan operasi pemantauan aktivitas interaksi
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if(s.toString().equals("")){
                mSaveButton.setEnabled(false);
                mSaveButton.setBackgroundColor(Color.GRAY);
            }else{
                mSaveButton.setEnabled(true);
                mSaveButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    });
//        Event save button ketika di klik
        final boolean finalIsUpdate = isUpdate;
        mSaveButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String text = mEditText.getText().toString();

            if(finalIsUpdate){
                myDB.UpdateTask(bundle.getInt("ID"), text);
            }else{
                ToDoModel item = new ToDoModel();
                item.setTask(text);
                item.setStatus(0);
                myDB.InsertTask(item);
            }
            dismiss();
        }
    });
    }

//     On Dismiss

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
        if(activity instanceof onDialogCloseListener){
            ((onDialogCloseListener)activity).onDialogClose(dialog);

        }
    }
}
