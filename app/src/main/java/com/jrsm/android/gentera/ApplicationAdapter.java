package com.jrsm.android.gentera;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by joseroberto on 18/08/16.
 */
public class ApplicationAdapter extends ArrayAdapter<ApplicationInfo> {

    private List<ApplicationInfo> appList = null;
    private Context context;
    private PackageManager packageManager;


    public ApplicationAdapter(Context context, int textViewResouceId,List<ApplicationInfo> appList) {
        super(context, textViewResouceId,appList);
        this.context = context;
        this.appList = appList;
        packageManager = context.getPackageManager();
    }

    @Override
    public int getCount(){
        return ((null != appList) ? appList.size():0);
    }
    @Override
    public ApplicationInfo getItem(int position){
        return ((null != appList) ? appList.get(position) : null);
    }
    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        if (null == view){
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.snipped_list_row,null);
        }

        ApplicationInfo applicationInfo = appList.get(position);
        if (null != applicationInfo){
            TextView appName = (TextView) view.findViewById(R.id.app_name);
            TextView packageName = (TextView) view.findViewById(R.id.app_paackage);
            ImageView iconView = (ImageView) view.findViewById(R.id.app_icon);

            appName.setText(applicationInfo.loadLabel(packageManager));
            packageName.setText(applicationInfo.packageName);
            iconView.setImageDrawable(applicationInfo.loadIcon(packageManager));
        }
        return view;
    }
}
