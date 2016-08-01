package com.binvshe.binvshe.adapter.meadapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.activity.ActivityQrCodeDetailActivity;
import com.binvshe.binvshe.constants.GlobalConfig;
import com.binvshe.binvshe.entity.GetTicketList.TicketData;

import org.srr.dev.adapter.RecyclerViewDataAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 门票适配器
 */
//public class TicketAdapter extends ChenRecyclerBaseAdapter<TicketAdapter.MyHolder> {
//
//    private static final int QR_WIDTH = 300;
//    private static final int QR_HEIGHT = 300;
//
//    private List<TicketData> mList = new ArrayList<>();
//
//    public TicketAdapter(List<TicketData> listTicket) {
//        mList = listTicket;
//    }
//
//    @Override
//    public void myBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
//        MyHolder holder = (MyHolder) viewHolder;
//        TicketData ticketData = mList.get(position);
////        Bitmap bitmap = QrCodeUtil.createQRImage(QR_WIDTH, QR_HEIGHT, ticketData.getNumcode());
////        holder.iv_qr_code.setImageBitmap(bitmap);
//        holder.tv_zxing.setText(ticketData.getActivityName());
//
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qr_code_layout, null);
//        MyHolder holder = new MyHolder(view);
//        return holder;
//    }
//
//    @Override
//    public int getItemCount() {
//        return mList.size();
//    }
//
//    public class MyHolder extends RecyclerView.ViewHolder {
//
//        ImageView iv_qr_code;
//        TextView tv_zxing;
//
//        public MyHolder(View itemView) {
//            super(itemView);
//            iv_qr_code = (ImageView) itemView.findViewById(R.id.iv_qr_code);
//            tv_zxing = (TextView) itemView.findViewById(R.id.tv_zxing);
//        }
//    }
//
//}
public class TicketAdapter extends RecyclerViewDataAdapter<TicketData, TicketAdapter.Holder> {


    @Override
    public void onBindHolder(Holder viewHolder, int i, TicketData ticketData) {
        viewHolder.tvZxing.setText(ticketData.getActivityName()+"");
    }

    @Override
    public Holder getViewHolder(View view) {
        return new Holder(view);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_qr_code_layout;
    }



    public class Holder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_zxing)
        TextView tvZxing;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
