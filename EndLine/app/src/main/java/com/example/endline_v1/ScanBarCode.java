package com.example.endline_v1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class ScanBarCode extends AppCompatActivity {

    EditText et_barcode, et_productName, et_category, et_brand, et_price, et_buyDay, et_endline;
    Button btn_buyDatePicker, btn_endLinePicker, btn_insertScan, btn_cancelScan;
    Spinner spinner;
    Map<String, Object> data = new HashMap<>();
    Calendar c = Calendar.getInstance();

    //10.20 ??????
    Uri imageUri;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    String strUrl;
    ImageView iv;
    // ????????????

    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseFirestore firestore;
    private CollectionReference collectionReference;
    private Context context;
    private Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanpage);

//        getSupportActionBar().setTitle("?????? ??????");

        //10.20 ??????
        iv = (ImageView)findViewById(R.id.imgview);
        // ????????????

        et_barcode = (EditText) findViewById(R.id.et_barcode);
        et_productName = (EditText) findViewById(R.id.et_productName);
        et_brand = (EditText) findViewById(R.id.et_brand);
        et_price = (EditText) findViewById(R.id.et_price);
        et_category = (EditText) findViewById(R.id.et_category);
        et_buyDay = (EditText) findViewById(R.id.et_buyDay);
        et_endline = (EditText) findViewById(R.id.et_endline);
        btn_buyDatePicker = (Button) findViewById(R.id.btn_buyDatePicker);
        btn_endLinePicker = (Button) findViewById(R.id.btn_endLinePicker);
        btn_insertScan = (Button) findViewById(R.id.btn_insertScan);
        btn_cancelScan = (Button) findViewById(R.id.btn_cancelScan);
        spinner = (Spinner) findViewById(R.id.spinner);

        //10.30 ??????, ?????? ?????? ??????
        et_barcode.setFilters(new InputFilter[] { editFilter });
        et_price.setFilters(new InputFilter[] { editFilter });

        //10.20 ??????, ??????????????? ?????? ??????
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        // ????????????

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                et_category.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        DatePickerDialog.OnDateSetListener buyDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                et_buyDay.setText(year + "-" + (month+1) + "-" + dayOfMonth);
            }
        };

        DatePickerDialog.OnDateSetListener endLineDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                et_endline.setText(year + "-" + (month+1) + "-" + dayOfMonth);
            }
        };

        View.OnClickListener showDatePicker = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.btn_buyDatePicker){
                    new DatePickerDialog(
                            ScanBarCode.this,
                            buyDateSetListener,
                            c.get(Calendar.YEAR),
                            c.get(Calendar.MONTH),
                            c.get(Calendar.DAY_OF_MONTH)).show();
                }else if(v.getId() == R.id.btn_endLinePicker){
                    new DatePickerDialog(
                            ScanBarCode.this,
                            endLineDateSetListener,
                            c.get(Calendar.YEAR),
                            c.get(Calendar.MONTH),
                            c.get(Calendar.DAY_OF_MONTH)).show();
                }

            }
        };

        btn_buyDatePicker.setOnClickListener(showDatePicker);
        btn_endLinePicker.setOnClickListener(showDatePicker);

        btn_insertScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(getApplicationContext(), "?????? ??????", Toast.LENGTH_SHORT).show();

                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                FirebaseAuth user = FirebaseAuth.getInstance();
                // Toast.makeText(getApplicationContext(), user.getUid(), Toast.LENGTH_SHORT).show(); // 10.30 ?????? ???????????? ????????? ??? ?????? ??????

                //10.20 ??????
                //progressDialog = new ProgressDialog(getApplicationContext());
                //progressDialog.setTitle("Uploading File...");
                //progressDialog.show();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.KOREA); //????????????
                Date now = new Date();
                String fileName = formatter.format(now);
                storageReference = FirebaseStorage.getInstance().getReference("images/" + fileName);
                //??????????????? images ????????? ???????????? ????????? ?????? ??????

                storageReference.putFile(imageUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String strUrl = uri.toString();

                                        data.put("UID", user.getUid());
                                        data.put("barcode", et_barcode.getText().toString().substring(9));
                                        data.put("register_date", getTime());
                                        data.put("product_name", et_productName.getText().toString());
                                        data.put("category", et_category.getText().toString());
                                        data.put("brand", et_brand.getText().toString());
                                        data.put("price", et_price.getText().toString());
                                        data.put("buy_date", et_buyDay.getText().toString());
                                        data.put("end_line", et_endline.getText().toString());
                                        data.put("use_state", "?????????");
                                        data.put("img",strUrl);

                                        firestore.collection("mainData").add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Log.d("Firestore ??????", "write data to firebase");
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w("firestore input", "write data fail");
                                            }
                                        });
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Failed to Upload", Toast.LENGTH_LONG).show();
                    }
                });
                // 10.20 ????????????
            }
        });

        btn_cancelScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(getApplicationContext(), "??????", Toast.LENGTH_SHORT).show();
            }
        });

        new IntentIntegrator(this).initiateScan();

    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
    }

    //10.30 ??????, ?????? ?????????
    protected InputFilter editFilter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Pattern pattern = Pattern.compile("^[0-9]*$");        // ??????
            // Pattern pattern = Pattern.compile("^[a-zA-Z]+$");        // ??????
            // Pattern pattern = Pattern.compile("^[???-??????-???]+$");        // ??????
            // Pattern pattern = Pattern.compile("^[a-zA-Z0-9???-??????-???]+$");        // ??????,??????,??????
            if(!pattern.matcher(source).matches()) {
                Toast.makeText(getApplicationContext(), "????????? ??????????????????", Toast.LENGTH_SHORT).show();
                return "";
            }
            return null;
        }
    };

     //10.30 ??????, ???????????? ????????? ??????????????? ?????? ????????? ????????? ???????????? ????????????, ????????? ????????? ????????? ?????????????????? ??????
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && data != null && data.getData() != null)
        {
            imageUri = data.getData();
            iv.setImageURI(imageUri);
        }
        else if (IntentIntegrator.parseActivityResult(requestCode, resultCode, data) != null){
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

            if(result.getContents() != null) { // ????????? ????????? ?????????
                String barcodeNum = result.getContents();

                auth = FirebaseAuth.getInstance();
                user = auth.getCurrentUser();
                firestore = FirebaseFirestore.getInstance();
                collectionReference = firestore.collection("mainData");

                query = collectionReference.whereEqualTo("barcode", barcodeNum).whereEqualTo("UID", user.getUid());
                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("getData", document.getData().toString());
                                et_barcode.setText(document.get("barcode").toString());
                                et_productName.setText(document.get("product_name").toString());
                                et_brand.setText(document.get("brand").toString());
                                Glide.with(getApplicationContext()).load(document.get("img").toString()).into(iv);
                            }
                        } else {
                            Log.w("getData", "fail");
                            Toast.makeText(context, "???????????? ?????? ??? ????????????.", Toast.LENGTH_LONG).show();
                            Intent directlyadd = new Intent(getApplicationContext(),DirectlyAdd.class);
                            startActivity(directlyadd);
                            finish();
                        }
                    }
                });
            }
            else{
                //et_barcode.setEnabled(true);
                Intent directlyadd = new Intent(getApplicationContext(),DirectlyAdd.class);
                startActivity(directlyadd);
                finish();
            }
        }
    }

    private String getTime(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = dateFormat.format(date);
        return getTime;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
