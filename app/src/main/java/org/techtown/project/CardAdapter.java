package org.techtown.project;


import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

import org.techtown.project.databinding.CarditemHighBinding;
import org.techtown.project.databinding.CarditemLowBinding;
import org.techtown.project.databinding.CarditemMediumBinding;
import org.techtown.project.util.CommonClickListener;

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int[] lowImgs = new int[10];
    private int[] mediumImgs = new int[18];
    private int[] highImgs = new int[28];
    private int[] backImgs = {R.drawable.cardback, R.drawable.cardback, R.drawable.flagback};
    private int[] tarotLowImgs = {R.drawable.tarot1, R.drawable.tarot1, R.drawable.tarot2, R.drawable.tarot2, R.drawable.tarot3, R.drawable.tarot3, R.drawable.tarot4, R.drawable.tarot4, R.drawable.tarot5, R.drawable.tarot5};
    private int[] tarotMediumImgs = {R.drawable.tarot1, R.drawable.tarot1, R.drawable.tarot2, R.drawable.tarot2, R.drawable.tarot3, R.drawable.tarot3, R.drawable.tarot4, R.drawable.tarot4, R.drawable.tarot5, R.drawable.tarot5, R.drawable.tarot6, R.drawable.tarot6, R.drawable.tarot7, R.drawable.tarot7, R.drawable.tarot8, R.drawable.tarot8, R.drawable.tarot9, R.drawable.tarot9};
    private int[] tarotHighImgs = {R.drawable.tarot1, R.drawable.tarot1, R.drawable.tarot2, R.drawable.tarot2, R.drawable.tarot3, R.drawable.tarot3, R.drawable.tarot4, R.drawable.tarot4, R.drawable.tarot5, R.drawable.tarot5, R.drawable.tarot6, R.drawable.tarot6, R.drawable.tarot7, R.drawable.tarot7, R.drawable.tarot8, R.drawable.tarot8, R.drawable.tarot9, R.drawable.tarot9, R.drawable.tarot10, R.drawable.tarot10,
            R.drawable.tarot11, R.drawable.tarot11, R.drawable.tarot12, R.drawable.tarot12, R.drawable.tarot13, R.drawable.tarot13, R.drawable.tarot14, R.drawable.tarot14};
    private int[] flagLowImgs = {R.drawable.arg, R.drawable.arg, R.drawable.bra, R.drawable.bra, R.drawable.fra, R.drawable.fra, R.drawable.kor, R.drawable.kor, R.drawable.usa, R.drawable.usa};
    private int[] flagMediumImgs = {R.drawable.arg, R.drawable.arg, R.drawable.bra, R.drawable.bra, R.drawable.fra, R.drawable.fra, R.drawable.kor, R.drawable.kor, R.drawable.usa, R.drawable.usa, R.drawable.can, R.drawable.can, R.drawable.eth, R.drawable.eth,
            R.drawable.ger, R.drawable.ger, R.drawable.gha, R.drawable.gha};
    private int[] flagHighImgs = {R.drawable.arg, R.drawable.arg, R.drawable.bra, R.drawable.bra, R.drawable.fra, R.drawable.fra, R.drawable.kor, R.drawable.kor, R.drawable.usa, R.drawable.usa, R.drawable.can, R.drawable.can, R.drawable.eth, R.drawable.eth,
            R.drawable.ger, R.drawable.ger, R.drawable.gha, R.drawable.gha, R.drawable.ind, R.drawable.ind, R.drawable.irl, R.drawable.irl, R.drawable.ita, R.drawable.ita, R.drawable.mex, R.drawable.mex, R.drawable.rus, R.drawable.rus};
    private int[] animalLowImgs = {R.drawable.animal1, R.drawable.animal1, R.drawable.animal2, R.drawable.animal2, R.drawable.animal3, R.drawable.animal3, R.drawable.animal4, R.drawable.animal4, R.drawable.animal5, R.drawable.animal5};
    private int[] animalMediumImgs = {R.drawable.animal1, R.drawable.animal1, R.drawable.animal2, R.drawable.animal2, R.drawable.animal3, R.drawable.animal3, R.drawable.animal4, R.drawable.animal4, R.drawable.animal5, R.drawable.animal5, R.drawable.animal6,
            R.drawable.animal6, R.drawable.animal7, R.drawable.animal7, R.drawable.animal8, R.drawable.animal8, R.drawable.animal9, R.drawable.animal9};
    private int[] animalHighImgs = {R.drawable.animal1, R.drawable.animal1, R.drawable.animal2, R.drawable.animal2, R.drawable.animal3, R.drawable.animal3, R.drawable.animal4, R.drawable.animal4, R.drawable.animal5, R.drawable.animal5, R.drawable.animal6,
            R.drawable.animal6, R.drawable.animal7, R.drawable.animal7, R.drawable.animal8, R.drawable.animal8, R.drawable.animal9, R.drawable.animal9, R.drawable.animal10, R.drawable.animal10, R.drawable.animal11, R.drawable.animal11, R.drawable.animal12, R.drawable.animal12, R.drawable.animal13, R.drawable.animal13, R.drawable.animal14, R.drawable.animal14};
    ArrayList<Card> cardList = new ArrayList<>();
    private static final int VIEW_TYPE_A = 0;
    private static final int VIEW_TYPE_B = 1;
    private static final int VIEW_TYPE_C = 2;
    private static final int VIEW_TYPE_D = 3;
    private static final int VIEW_TYPE_E = 4;
    private static final int VIEW_TYPE_F = 5;
    private static final int VIEW_TYPE_G = 6;
    private static final int VIEW_TYPE_H = 7;
    private static final int VIEW_TYPE_I = 8;
    private FlipAnimation flipAnimation, flipAnimation2, flipAnimation3, flipAnimation4, flipAnimation5, flipAnimation6, flipAnimation7, flipAnimation8, flipAnimation9, flipAnimation10, flipAnimation11, flipAnimation12, flipAnimation13, flipAnimation14,
            flipAnimation15, flipAnimation16, flipAnimation17, flipAnimation18, flipAnimation19, flipAnimation20, flipAnimation21, flipAnimation22, flipAnimation23, flipAnimation24, flipAnimation25, flipAnimation26, flipAnimation27, flipAnimation28;
    Handler handler;
    private Context context;
    CardActivity cardActivity;
    int id = 0;
    int subid = 0;
    private Runnable runnable;
    private Runnable runnable3;
    private Animation alpa;
    private String TAG = "Adapter";
    int sameImage;
    int[] a;
    int clickcount;
    int same;
    int nameNum;
    View.OnClickListener click;
    static CountDownTimer timer2;


    CardAdapter(Context context) {
        this.context = context;
    }

    void random(int randomNum) {          //랜덤메소드
        a = new int[randomNum];
        Random r = new Random();
        for (int i = 0; i < randomNum; i++) {
            a[i] = r.nextInt(randomNum);
            for (int j = 0; j < i; j++) {
                if (a[i] == a[j]) {
                    i--;
                }
            }

        }
    }


    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_A || viewType == VIEW_TYPE_D || viewType == VIEW_TYPE_G) {
            random(28);
            int back[] = new int[1];
            if (viewType == VIEW_TYPE_A) {
                for (int i = 0; i < tarotHighImgs.length; i++) {
                    highImgs[i] = tarotHighImgs[i];
                    back[0] = backImgs[0];
                    nameNum = 1;
                    Log.d(TAG, "highImgs : " + highImgs[i]);
                }
            } else if (viewType == VIEW_TYPE_D) {
                for (int i = 0; i < animalHighImgs.length; i++) {
                    highImgs[i] = animalHighImgs[i];
                    back[0] = backImgs[1];
                    nameNum = 2;
                    Log.d(TAG, "highImgs : " + highImgs[i]);
                }
            } else if (viewType == VIEW_TYPE_G) {
                for (int i = 0; i < flagHighImgs.length; i++) {
                    highImgs[i] = flagHighImgs[i];
                    back[0] = backImgs[2];
                    nameNum = 3;
                    Log.d(TAG, "highImgs : " + highImgs[i]);
                }
            }
            CarditemHighBinding card_high = DataBindingUtil.inflate(inflator, R.layout.carditem_high, parent, false);        //이미지를 무작위로 하기위해 랜덤메소드를 이용해
            card_high.highCardBack1.setBackground(ContextCompat.getDrawable(context, highImgs[a[0]]));
            card_high.highCardBack2.setBackground(ContextCompat.getDrawable(context, highImgs[a[1]]));
            card_high.highCardBack3.setBackground(ContextCompat.getDrawable(context, highImgs[a[2]]));
            card_high.highCardBack4.setBackground(ContextCompat.getDrawable(context, highImgs[a[3]]));
            card_high.highCardBack5.setBackground(ContextCompat.getDrawable(context, highImgs[a[4]]));
            card_high.highCardBack6.setBackground(ContextCompat.getDrawable(context, highImgs[a[5]]));
            card_high.highCardBack7.setBackground(ContextCompat.getDrawable(context, highImgs[a[6]]));
            card_high.highCardBack8.setBackground(ContextCompat.getDrawable(context, highImgs[a[7]]));
            card_high.highCardBack9.setBackground(ContextCompat.getDrawable(context, highImgs[a[8]]));
            card_high.highCardBack10.setBackground(ContextCompat.getDrawable(context, highImgs[a[9]]));
            card_high.highCardBack11.setBackground(ContextCompat.getDrawable(context, highImgs[a[10]]));
            card_high.highCardBack12.setBackground(ContextCompat.getDrawable(context, highImgs[a[11]]));
            card_high.highCardBack13.setBackground(ContextCompat.getDrawable(context, highImgs[a[12]]));
            card_high.highCardBack14.setBackground(ContextCompat.getDrawable(context, highImgs[a[13]]));
            card_high.highCardBack15.setBackground(ContextCompat.getDrawable(context, highImgs[a[14]]));
            card_high.highCardBack16.setBackground(ContextCompat.getDrawable(context, highImgs[a[15]]));
            card_high.highCardBack17.setBackground(ContextCompat.getDrawable(context, highImgs[a[16]]));
            card_high.highCardBack18.setBackground(ContextCompat.getDrawable(context, highImgs[a[17]]));
            card_high.highCardBack19.setBackground(ContextCompat.getDrawable(context, highImgs[a[18]]));
            card_high.highCardBack20.setBackground(ContextCompat.getDrawable(context, highImgs[a[19]]));
            card_high.highCardBack21.setBackground(ContextCompat.getDrawable(context, highImgs[a[20]]));
            card_high.highCardBack22.setBackground(ContextCompat.getDrawable(context, highImgs[a[21]]));
            card_high.highCardBack23.setBackground(ContextCompat.getDrawable(context, highImgs[a[22]]));
            card_high.highCardBack24.setBackground(ContextCompat.getDrawable(context, highImgs[a[23]]));
            card_high.highCardBack25.setBackground(ContextCompat.getDrawable(context, highImgs[a[24]]));
            card_high.highCardBack26.setBackground(ContextCompat.getDrawable(context, highImgs[a[25]]));
            card_high.highCardBack27.setBackground(ContextCompat.getDrawable(context, highImgs[a[26]]));
            card_high.highCardBack28.setBackground(ContextCompat.getDrawable(context, highImgs[a[27]]));
            card_high.highCardFront1.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront2.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront3.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront4.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront5.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront6.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront7.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront8.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront9.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront10.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront11.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront12.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront13.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront14.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront15.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront16.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront17.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront18.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront19.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront20.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront21.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront22.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront23.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront24.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront25.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront26.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront27.setBackground(ContextCompat.getDrawable(context, back[0]));
            card_high.highCardFront28.setBackground(ContextCompat.getDrawable(context, back[0]));
            Log.d(TAG, "a : " + a.length);
            //            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.carditem_high, parent, false);
            return new A1ViewHolder(card_high, context, nameNum);
        } else if (viewType == VIEW_TYPE_B || viewType == VIEW_TYPE_E || viewType == VIEW_TYPE_H) {
            random(18);
            int back[] = new int[1];
            if (viewType == VIEW_TYPE_B) {
                for (int i = 0; i < tarotMediumImgs.length; i++) {
                    mediumImgs[i] = tarotMediumImgs[i];
                    back[0] = backImgs[0];
                    nameNum = 1;
                    Log.d(TAG, "mediumImgs : " + mediumImgs[i]);
                }
            } else if (viewType == VIEW_TYPE_E) {
                for (int i = 0; i < animalMediumImgs.length; i++) {
                    mediumImgs[i] = animalMediumImgs[i];
                    back[0] = backImgs[1];
                    nameNum = 2;
                    Log.d(TAG, "mediumImgs : " + mediumImgs[i]);
                }

            } else if (viewType == VIEW_TYPE_H) {
                for (int i = 0; i < flagMediumImgs.length; i++) {
                    mediumImgs[i] = flagMediumImgs[i];
                    back[0] = backImgs[2];
                    nameNum = 3;
                    Log.d(TAG, "mediumImgs : " + mediumImgs[i]);
                }

            }
            CarditemMediumBinding carditem_medium = DataBindingUtil.inflate(inflator, R.layout.carditem_medium, parent, false);         //이미지를 무작위로 하기위해 랜덤메소드를 이용해
            carditem_medium.mediumCardBack1.setBackground(ContextCompat.getDrawable(context, mediumImgs[a[0]]));
            carditem_medium.mediumCardBack2.setBackground(ContextCompat.getDrawable(context, mediumImgs[a[1]]));
            carditem_medium.mediumCardBack3.setBackground(ContextCompat.getDrawable(context, mediumImgs[a[2]]));
            carditem_medium.mediumCardBack4.setBackground(ContextCompat.getDrawable(context, mediumImgs[a[3]]));
            carditem_medium.mediumCardBack5.setBackground(ContextCompat.getDrawable(context, mediumImgs[a[4]]));
            carditem_medium.mediumCardBack6.setBackground(ContextCompat.getDrawable(context, mediumImgs[a[5]]));
            carditem_medium.mediumCardBack7.setBackground(ContextCompat.getDrawable(context, mediumImgs[a[6]]));
            carditem_medium.mediumCardBack8.setBackground(ContextCompat.getDrawable(context, mediumImgs[a[7]]));
            carditem_medium.mediumCardBack9.setBackground(ContextCompat.getDrawable(context, mediumImgs[a[8]]));
            carditem_medium.mediumCardBack10.setBackground(ContextCompat.getDrawable(context, mediumImgs[a[9]]));
            carditem_medium.mediumCardBack11.setBackground(ContextCompat.getDrawable(context, mediumImgs[a[10]]));
            carditem_medium.mediumCardBack12.setBackground(ContextCompat.getDrawable(context, mediumImgs[a[11]]));
            carditem_medium.mediumCardBack13.setBackground(ContextCompat.getDrawable(context, mediumImgs[a[12]]));
            carditem_medium.mediumCardBack14.setBackground(ContextCompat.getDrawable(context, mediumImgs[a[13]]));
            carditem_medium.mediumCardBack15.setBackground(ContextCompat.getDrawable(context, mediumImgs[a[14]]));
            carditem_medium.mediumCardBack16.setBackground(ContextCompat.getDrawable(context, mediumImgs[a[15]]));
            carditem_medium.mediumCardBack17.setBackground(ContextCompat.getDrawable(context, mediumImgs[a[16]]));
            carditem_medium.mediumCardBack18.setBackground(ContextCompat.getDrawable(context, mediumImgs[a[17]]));
            carditem_medium.mediumCardFront1.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_medium.mediumCardFront2.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_medium.mediumCardFront3.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_medium.mediumCardFront4.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_medium.mediumCardFront5.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_medium.mediumCardFront6.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_medium.mediumCardFront7.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_medium.mediumCardFront8.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_medium.mediumCardFront9.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_medium.mediumCardFront10.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_medium.mediumCardFront11.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_medium.mediumCardFront12.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_medium.mediumCardFront13.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_medium.mediumCardFront14.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_medium.mediumCardFront15.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_medium.mediumCardFront16.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_medium.mediumCardFront17.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_medium.mediumCardFront18.setBackground(ContextCompat.getDrawable(context, back[0]));

//            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.carditem_medium, parent, false);
            Log.d(TAG, "a : " + a.length);
            return new A2ViewHolder(carditem_medium, context, nameNum);
        } else {
            random(10);
            int back[] = new int[1];
            if (viewType == VIEW_TYPE_C) {
                for (int i = 0; i < tarotLowImgs.length; i++) {
                    lowImgs[i] = tarotLowImgs[i];
                    back[0] = backImgs[0];
                    nameNum = 1;
                    Log.d(TAG, "lowImgs : " + lowImgs[i]);
                }
            } else if (viewType == VIEW_TYPE_F) {
                for (int i = 0; i < animalLowImgs.length; i++) {
                    lowImgs[i] = animalLowImgs[i];
                    back[0] = backImgs[1];
                    nameNum = 2;
                    Log.d(TAG, "lowImgs : " + lowImgs[i]);
                }
            } else if (viewType == VIEW_TYPE_I) {
                for (int i = 0; i < flagLowImgs.length; i++) {
                    lowImgs[i] = flagLowImgs[i];
                    back[0] = backImgs[2];
                    nameNum = 3;
                    Log.d(TAG, "lowImgs : " + lowImgs[i]);
                }
            }
            CarditemLowBinding carditem_low = DataBindingUtil.inflate(inflator, R.layout.carditem_low, parent, false);          //이미지를 무작위로 하기위해 랜덤메소드를 이용해
            carditem_low.lowCardBack1.setBackground(ContextCompat.getDrawable(context, lowImgs[a[0]]));
            carditem_low.lowCardBack2.setBackground(ContextCompat.getDrawable(context, lowImgs[a[1]]));
            carditem_low.lowCardBack3.setBackground(ContextCompat.getDrawable(context, lowImgs[a[2]]));
            carditem_low.lowCardBack4.setBackground(ContextCompat.getDrawable(context, lowImgs[a[3]]));
            carditem_low.lowCardBack5.setBackground(ContextCompat.getDrawable(context, lowImgs[a[4]]));
            carditem_low.lowCardBack6.setBackground(ContextCompat.getDrawable(context, lowImgs[a[5]]));
            carditem_low.lowCardBack7.setBackground(ContextCompat.getDrawable(context, lowImgs[a[6]]));
            carditem_low.lowCardBack8.setBackground(ContextCompat.getDrawable(context, lowImgs[a[7]]));
            carditem_low.lowCardBack9.setBackground(ContextCompat.getDrawable(context, lowImgs[a[8]]));
            carditem_low.lowCardBack10.setBackground(ContextCompat.getDrawable(context, lowImgs[a[9]]));
            carditem_low.lowCardFront1.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_low.lowCardFront2.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_low.lowCardFront3.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_low.lowCardFront4.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_low.lowCardFront5.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_low.lowCardFront6.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_low.lowCardFront7.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_low.lowCardFront8.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_low.lowCardFront9.setBackground(ContextCompat.getDrawable(context, back[0]));
            carditem_low.lowCardFront10.setBackground(ContextCompat.getDrawable(context, back[0]));


            Log.d(TAG, "a : " + a.length);
            return new A3ViewHolder(carditem_low, context, nameNum);
        }
    }

    public int getItemViewType(int position) {
        if (cardList.get(position).getItemViewType() == 0) {
            return VIEW_TYPE_A;
        } else if (cardList.get(position).getItemViewType() == 1) {
            return VIEW_TYPE_B;
        } else if (cardList.get(position).getItemViewType() == 2) {
            return VIEW_TYPE_C;
        } else if (cardList.get(position).getItemViewType() == 3) {
            return VIEW_TYPE_D;
        } else if (cardList.get(position).getItemViewType() == 4) {
            return VIEW_TYPE_E;
        } else if (cardList.get(position).getItemViewType() == 5) {
            return VIEW_TYPE_F;
        } else if (cardList.get(position).getItemViewType() == 6) {
            return VIEW_TYPE_G;
        } else if (cardList.get(position).getItemViewType() == 7) {
            return VIEW_TYPE_H;
        } else {
            return VIEW_TYPE_I;
        }
    }

    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final Card card = cardList.get(position);
        if (holder instanceof A1ViewHolder) {
            ((A1ViewHolder) holder).start();
            Runnable run5 = new Runnable() {
                @Override
                public void run() {
                    ((A1ViewHolder) holder).reverse();
                }
            };

            handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(run5, 3000);
        } else if (holder instanceof A2ViewHolder) {
            ((A2ViewHolder) holder).start();
            Runnable run5 = new Runnable() {
                @Override
                public void run() {
                    ((A2ViewHolder) holder).reverse();
                }
            };

            handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(run5, 3000);
        } else if (holder instanceof A3ViewHolder) {
            ((A3ViewHolder) holder).start();
            Runnable run5 = new Runnable() {
                @Override
                public void run() {
                    ((A3ViewHolder) holder).reverse();
                }
            };

            handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(run5, 3000);
        }
    }

    public void addItem(Card card) {
        cardList.add(card);
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return cardList.size();
    }

    public class A1ViewHolder extends CustomViewHolder {
        CarditemHighBinding binding;

        public A1ViewHolder(final CarditemHighBinding binding, final Context context, final int nameNum) {
            super(binding);
            this.binding = binding;
            alpa = AnimationUtils.loadAnimation(context, R.anim.cardani);      //효과 애니메이션
            click = new CommonClickListener() {
                @Override
                public void performClick(View v) {

                    if (clickcount <= 2) {
                        clickcount++;
                        if (v == binding.highCardFront1) {
                            flipCard(1);
                            id = 1;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[0]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[0]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[0]];
                                }
                            }
                        } else if (v == binding.highCardFront2) {
                            flipCard(2);
                            id = 2;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[1]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[1]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[1]];
                                }
                            }
                        } else if (v == binding.highCardFront3) {
                            flipCard(3);
                            id = 3;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[2]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[2]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[2]];
                                }
                            }
                        } else if (v == binding.highCardFront4) {
                            flipCard(4);
                            id = 4;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[3]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[3]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[3]];
                                }
                            }
                        } else if (v == binding.highCardFront5) {
                            flipCard(5);
                            id = 5;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[4]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[4]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[4]];
                                }
                            }
                        } else if (v == binding.highCardFront6) {
                            flipCard(6);
                            id = 6;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[5]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[5]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[5]];
                                }
                            }
                        } else if (v == binding.highCardFront7) {
                            flipCard(7);
                            id = 7;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[6]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[6]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[6]];
                                }
                            }
                        } else if (v == binding.highCardFront8) {
                            flipCard(8);
                            id = 8;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[7]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[7]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[7]];
                                }
                            }
                        } else if (v == binding.highCardFront9) {
                            flipCard(9);
                            id = 9;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[8]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[8]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[8]];
                                }
                            }
                        } else if (v == binding.highCardFront10) {
                            flipCard(10);
                            id = 10;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[9]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[9]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[9]];
                                }
                            }
                        } else if (v == binding.highCardFront11) {
                            flipCard(11);
                            id = 11;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[10]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[10]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[10]];
                                }
                            }
                        } else if (v == binding.highCardFront12) {
                            flipCard(12);
                            id = 12;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[11]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[11]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[11]];
                                }
                            }
                        } else if (v == binding.highCardFront13) {
                            flipCard(13);
                            id = 13;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[12]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[12]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[12]];
                                }
                            }
                        } else if (v == binding.highCardFront14) {
                            flipCard(14);
                            id = 14;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[13]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[13]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[13]];
                                }
                            }
                        } else if (v == binding.highCardFront15) {
                            flipCard(15);
                            id = 15;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[14]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[14]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[14]];
                                }
                            }
                        } else if (v == binding.highCardFront16) {
                            flipCard(16);
                            id = 16;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[15]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[15]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[15]];
                                }
                            }
                        } else if (v == binding.highCardFront17) {
                            flipCard(17);
                            id = 17;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[16]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[16]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[16]];
                                }
                            }
                        } else if (v == binding.highCardFront18) {
                            flipCard(18);
                            id = 18;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[17]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[17]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[17]];
                                }
                            }
                        } else if (v == binding.highCardFront19) {
                            flipCard(19);
                            id = 19;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[18]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[19]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[18]];
                                }
                            }
                        } else if (v == binding.highCardFront20) {
                            flipCard(20);
                            id = 20;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[19]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[19]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[19]];
                                }
                            }
                        } else if (v == binding.highCardFront21) {
                            flipCard(21);
                            id = 21;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[20]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[20]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[20]];
                                }
                            }
                        } else if (v == binding.highCardFront22) {
                            flipCard(22);
                            id = 22;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[21]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[21]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[21]];
                                }
                            }
                        } else if (v == binding.highCardFront23) {
                            flipCard(23);
                            id = 23;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[22]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[22]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[22]];
                                }
                            }
                        } else if (v == binding.highCardFront24) {
                            flipCard(24);
                            id = 24;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[23]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[23]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[23]];
                                }
                            }
                        } else if (v == binding.highCardFront25) {
                            flipCard(25);
                            id = 25;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[24]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[24]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[24]];
                                }
                            }
                        } else if (v == binding.highCardFront26) {
                            flipCard(26);
                            id = 26;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[25]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[25]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[25]];
                                }
                            }
                        } else if (v == binding.highCardFront27) {
                            flipCard(27);
                            id = 27;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[26]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[26]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[26]];
                                }
                            }
                        } else if (v == binding.highCardFront28) {
                            flipCard(28);
                            id = 28;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotHighImgs[a[27]];
                                } else if (nameNum == 2) {
                                    sameImage = animalHighImgs[a[27]];
                                } else if (nameNum == 3) {
                                    sameImage = flagHighImgs[a[27]];
                                }
                            }
                        }
                        if (clickcount == 1) {
                            same = sameImage;
                            subid = id;
                            Log.d(TAG, "same : " + same);
                        }
                        if (clickcount == 2) {
                            click2();
                            if (sameImage == same) {        //첫번째 클릭과 두번째 클릭의 이미지값이 같으면 조건이 발동
                                runnable3 = new Runnable() {  //카드 2개 맞추고 사라지기
                                    @Override
                                    public void run() {//조건이 맞는 값이 있으면 카드2개가 맞았다고 효과를 준다
                                        if ((sameImage == tarotHighImgs[a[0]] || same == tarotHighImgs[a[0]]) || (sameImage == animalHighImgs[a[0]] || same == animalHighImgs[a[0]]) || (sameImage == flagHighImgs[a[0]] || same == flagHighImgs[a[0]])) {
                                            binding.highEqualImpact1.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact1.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[1]] || same == tarotHighImgs[a[1]]) || (sameImage == animalHighImgs[a[1]] || same == animalHighImgs[a[1]]) || (sameImage == flagHighImgs[a[1]] || same == flagHighImgs[a[1]])) {
                                            binding.highEqualImpact2.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact2.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[2]] || same == tarotHighImgs[a[2]]) || (sameImage == animalHighImgs[a[2]] || same == animalHighImgs[a[2]]) || (sameImage == flagHighImgs[a[2]] || same == flagHighImgs[a[2]])) {
                                            binding.highEqualImpact3.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact3.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[3]] || same == tarotHighImgs[a[3]]) || (sameImage == animalHighImgs[a[3]] || same == animalHighImgs[a[3]]) || (sameImage == flagHighImgs[a[3]] || same == flagHighImgs[a[3]])) {
                                            binding.highEqualImpact4.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact4.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[4]] || same == tarotHighImgs[a[4]]) || (sameImage == animalHighImgs[a[4]] || same == animalHighImgs[a[4]]) || (sameImage == flagHighImgs[a[4]] || same == flagHighImgs[a[4]])) {
                                            binding.highEqualImpact5.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact5.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[5]] || same == tarotHighImgs[a[5]]) || (sameImage == animalHighImgs[a[5]] || same == animalHighImgs[a[5]]) || (sameImage == flagHighImgs[a[5]] || same == flagHighImgs[a[5]])) {
                                            binding.highEqualImpact6.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact6.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[6]] || same == tarotHighImgs[a[6]]) || (sameImage == animalHighImgs[a[6]] || same == animalHighImgs[a[6]]) || (sameImage == flagHighImgs[a[6]] || same == flagHighImgs[a[6]])) {
                                            binding.highEqualImpact7.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact7.startAnimation(alpa);

                                        }
                                        if ((sameImage == tarotHighImgs[a[7]] || same == tarotHighImgs[a[7]]) || (sameImage == animalHighImgs[a[7]] || same == animalHighImgs[a[7]]) || (sameImage == flagHighImgs[a[7]] || same == flagHighImgs[a[7]])) {
                                            binding.highEqualImpact8.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact8.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[8]] || same == tarotHighImgs[a[8]]) || (sameImage == animalHighImgs[a[8]] || same == animalHighImgs[a[8]]) || (sameImage == flagHighImgs[a[8]] || same == flagHighImgs[a[8]])) {
                                            binding.highEqualImpact9.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact9.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[9]] || same == tarotHighImgs[a[9]]) || (sameImage == animalHighImgs[a[9]] || same == animalHighImgs[a[9]]) || (sameImage == flagHighImgs[a[9]] || same == flagHighImgs[a[9]])) {
                                            binding.highEqualImpact10.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact10.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[10]] || same == tarotHighImgs[a[10]]) || (sameImage == animalHighImgs[a[10]] || same == animalHighImgs[a[10]]) || (sameImage == flagHighImgs[a[10]] || same == flagHighImgs[a[10]])) {
                                            binding.highEqualImpact11.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact11.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[11]] || same == tarotHighImgs[a[11]]) || (sameImage == animalHighImgs[a[11]] || same == animalHighImgs[a[11]]) || (sameImage == flagHighImgs[a[11]] || same == flagHighImgs[a[11]])) {
                                            binding.highEqualImpact12.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact12.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[12]] || same == tarotHighImgs[a[12]]) || (sameImage == animalHighImgs[a[12]] || same == animalHighImgs[a[12]]) || (sameImage == flagHighImgs[a[12]] || same == flagHighImgs[a[12]])) {
                                            binding.highEqualImpact13.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact13.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[13]] || same == tarotHighImgs[a[13]]) || (sameImage == animalHighImgs[a[13]] || same == animalHighImgs[a[13]]) || (sameImage == flagHighImgs[a[13]] || same == flagHighImgs[a[13]])) {
                                            binding.highEqualImpact14.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact14.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[14]] || same == tarotHighImgs[a[14]]) || (sameImage == animalHighImgs[a[14]] || same == animalHighImgs[a[14]]) || (sameImage == flagHighImgs[a[14]] || same == flagHighImgs[a[14]])) {
                                            binding.highEqualImpact15.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact15.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[15]] || same == tarotHighImgs[a[15]]) || (sameImage == animalHighImgs[a[15]] || same == animalHighImgs[a[15]]) || (sameImage == flagHighImgs[a[15]] || same == flagHighImgs[a[15]])) {
                                            binding.highEqualImpact16.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact16.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[16]] || same == tarotHighImgs[a[16]]) || (sameImage == animalHighImgs[a[16]] || same == animalHighImgs[a[16]]) || (sameImage == flagHighImgs[a[16]] || same == flagHighImgs[a[16]])) {
                                            binding.highEqualImpact17.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact17.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[17]] || same == tarotHighImgs[a[17]]) || (sameImage == animalHighImgs[a[17]] || same == animalHighImgs[a[17]]) || (sameImage == flagHighImgs[a[17]] || same == flagHighImgs[a[17]])) {
                                            binding.highEqualImpact18.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact18.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[18]] || same == tarotHighImgs[a[18]]) || (sameImage == animalHighImgs[a[18]] || same == animalHighImgs[a[18]]) || (sameImage == flagHighImgs[a[18]] || same == flagHighImgs[a[18]])) {
                                            binding.highEqualImpact19.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact19.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[19]] || same == tarotHighImgs[a[19]]) || (sameImage == animalHighImgs[a[19]] || same == animalHighImgs[a[19]]) || (sameImage == flagHighImgs[a[19]] || same == flagHighImgs[a[19]])) {
                                            binding.highEqualImpact20.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact20.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[20]] || same == tarotHighImgs[a[20]]) || (sameImage == animalHighImgs[a[20]] || same == animalHighImgs[a[20]]) || (sameImage == flagHighImgs[a[20]] || same == flagHighImgs[a[20]])) {
                                            binding.highEqualImpact21.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact21.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[21]] || same == tarotHighImgs[a[21]]) || (sameImage == animalHighImgs[a[21]] || same == animalHighImgs[a[21]]) || (sameImage == flagHighImgs[a[21]] || same == flagHighImgs[a[21]])) {
                                            binding.highEqualImpact22.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact22.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[22]] || same == tarotHighImgs[a[22]]) || (sameImage == animalHighImgs[a[22]] || same == animalHighImgs[a[22]]) || (sameImage == flagHighImgs[a[22]] || same == flagHighImgs[a[22]])) {
                                            binding.highEqualImpact23.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact23.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[23]] || same == tarotHighImgs[a[23]]) || (sameImage == animalHighImgs[a[23]] || same == animalHighImgs[a[23]]) || (sameImage == flagHighImgs[a[23]] || same == flagHighImgs[a[23]])) {
                                            binding.highEqualImpact24.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact24.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[24]] || same == tarotHighImgs[a[24]]) || (sameImage == animalHighImgs[a[24]] || same == animalHighImgs[a[24]]) || (sameImage == flagHighImgs[a[24]] || same == flagHighImgs[a[24]])) {
                                            binding.highEqualImpact25.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact25.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[25]] || same == tarotHighImgs[a[25]]) || (sameImage == animalHighImgs[a[25]] || same == animalHighImgs[a[25]]) || (sameImage == flagHighImgs[a[25]] || same == flagHighImgs[a[25]])) {
                                            binding.highEqualImpact26.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact26.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[26]] || same == tarotHighImgs[a[26]]) || (sameImage == animalHighImgs[a[26]] || same == animalHighImgs[a[26]]) || (sameImage == flagHighImgs[a[26]] || same == flagHighImgs[a[26]])) {
                                            binding.highEqualImpact27.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact27.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotHighImgs[a[27]] || same == tarotHighImgs[a[27]]) || (sameImage == animalHighImgs[a[27]] || same == animalHighImgs[a[27]]) || (sameImage == flagHighImgs[a[27]] || same == flagHighImgs[a[27]])) {
                                            binding.highEqualImpact28.setVisibility(View.VISIBLE);
                                            binding.highEqualImpact28.startAnimation(alpa);
                                        }
                                        if (binding.highCardFront1.getVisibility() == View.GONE && binding.highCardFront2.getVisibility() == View.GONE && binding.highCardFront3.getVisibility() == View.GONE && binding.highCardFront4.getVisibility() == View.GONE && binding.highCardFront5.getVisibility() == View.GONE &&
                                                binding.highCardFront6.getVisibility() == View.GONE && binding.highCardFront7.getVisibility() == View.GONE && binding.highCardFront8.getVisibility() == View.GONE && binding.highCardFront9.getVisibility() == View.GONE && binding.highCardFront10.getVisibility() == View.GONE &&
                                                binding.highCardFront11.getVisibility() == View.GONE && binding.highCardFront12.getVisibility() == View.GONE && binding.highCardFront13.getVisibility() == View.GONE && binding.highCardFront14.getVisibility() == View.GONE && binding.highCardFront15.getVisibility() == View.GONE &&
                                                binding.highCardFront16.getVisibility() == View.GONE && binding.highCardFront17.getVisibility() == View.GONE && binding.highCardFront18.getVisibility() == View.GONE && binding.highCardFront19.getVisibility() == View.GONE && binding.highCardFront20.getVisibility() == View.GONE &&
                                                binding.highCardFront21.getVisibility() == View.GONE && binding.highCardFront22.getVisibility() == View.GONE && binding.highCardFront23.getVisibility() == View.GONE && binding.highCardFront24.getVisibility() == View.GONE && binding.highCardFront25.getVisibility() == View.GONE &&
                                                binding.highCardFront26.getVisibility() == View.GONE && binding.highCardFront27.getVisibility() == View.GONE && binding.highCardFront28.getVisibility() == View.GONE) {
                                            cardActivity = new CardActivity();
                                            cardActivity.message("끝",context);
                                            Log.d(TAG, "end");
                                        }

                                        delay(200);
                                    }
                                };
                                handler = new Handler();
                                handler.postDelayed(runnable3, 500);
                                Log.d(TAG, "same");


                            } else {   //카드가 맞지않는 카드면은 다시 뒤집는다
                                click2();
                                Log.d(TAG, "run1");
                                runnable = new Runnable() {  //다시 뒤집기
                                    @Override
                                    public void run() {
                                        if (id == 1 || subid == 1) {
                                            flipAnimation.reverse();
                                            binding.highMainCard1.startAnimation(flipAnimation);
                                            binding.highEqualImpact1.setVisibility(View.GONE);
                                        }
                                        if (id == 2 || subid == 2) {
                                            flipAnimation2.reverse();
                                            binding.highMainCard2.startAnimation(flipAnimation2);
                                            binding.highEqualImpact2.setVisibility(View.GONE);
                                        }
                                        if (id == 3 || subid == 3) {
                                            flipAnimation3.reverse();
                                            binding.highMainCard3.startAnimation(flipAnimation3);
                                            binding.highEqualImpact3.setVisibility(View.GONE);
                                        }
                                        if (id == 4 || subid == 4) {
                                            flipAnimation4.reverse();
                                            binding.highMainCard4.startAnimation(flipAnimation4);
                                            binding.highEqualImpact4.setVisibility(View.GONE);
                                        }
                                        if (id == 5 || subid == 5) {
                                            flipAnimation5.reverse();
                                            binding.highMainCard5.startAnimation(flipAnimation5);
                                            binding.highEqualImpact5.setVisibility(View.GONE);
                                        }
                                        if (id == 6 || subid == 6) {
                                            flipAnimation6.reverse();
                                            binding.highMainCard6.startAnimation(flipAnimation6);
                                            binding.highEqualImpact6.setVisibility(View.GONE);
                                        }
                                        if (id == 7 || subid == 7) {
                                            flipAnimation7.reverse();
                                            binding.highMainCard7.startAnimation(flipAnimation7);
                                            binding.highEqualImpact7.setVisibility(View.GONE);

                                        }
                                        if (id == 8 || subid == 8) {
                                            flipAnimation8.reverse();
                                            binding.highMainCard8.startAnimation(flipAnimation8);
                                            binding.highEqualImpact8.setVisibility(View.GONE);
                                        }
                                        if (id == 9 || subid == 9) {
                                            flipAnimation9.reverse();
                                            binding.highMainCard9.startAnimation(flipAnimation9);
                                            binding.highEqualImpact9.setVisibility(View.GONE);
                                        }
                                        if (id == 10 || subid == 10) {
                                            flipAnimation10.reverse();
                                            binding.highMainCard10.startAnimation(flipAnimation10);
                                            binding.highEqualImpact10.setVisibility(View.GONE);
                                        }
                                        if (id == 11 || subid == 11) {
                                            flipAnimation11.reverse();
                                            binding.highMainCard11.startAnimation(flipAnimation11);
                                            binding.highEqualImpact11.setVisibility(View.GONE);
                                        }
                                        if (id == 12 || subid == 12) {
                                            flipAnimation12.reverse();
                                            binding.highMainCard12.startAnimation(flipAnimation12);
                                            binding.highEqualImpact12.setVisibility(View.GONE);
                                        }
                                        if (id == 13 || subid == 13) {
                                            flipAnimation13.reverse();
                                            binding.highMainCard13.startAnimation(flipAnimation13);
                                            binding.highEqualImpact13.setVisibility(View.GONE);
                                        }
                                        if (id == 14 || subid == 14) {
                                            flipAnimation14.reverse();
                                            binding.highMainCard14.startAnimation(flipAnimation14);
                                            binding.highEqualImpact14.setVisibility(View.GONE);
                                        }
                                        if (id == 15 || subid == 15) {
                                            flipAnimation15.reverse();
                                            binding.highMainCard15.startAnimation(flipAnimation15);
                                            binding.highEqualImpact15.setVisibility(View.GONE);
                                        }
                                        if (id == 16 || subid == 16) {
                                            flipAnimation16.reverse();
                                            binding.highMainCard16.startAnimation(flipAnimation16);
                                            binding.highEqualImpact16.setVisibility(View.GONE);
                                        }
                                        if (id == 17 || subid == 17) {
                                            flipAnimation17.reverse();
                                            binding.highMainCard17.startAnimation(flipAnimation17);
                                            binding.highEqualImpact17.setVisibility(View.GONE);
                                        }
                                        if (id == 18 || subid == 18) {
                                            flipAnimation18.reverse();
                                            binding.highMainCard18.startAnimation(flipAnimation18);
                                            binding.highEqualImpact18.setVisibility(View.GONE);
                                        }
                                        if (id == 19 || subid == 19) {
                                            flipAnimation19.reverse();
                                            binding.highMainCard19.startAnimation(flipAnimation19);
                                            binding.highEqualImpact19.setVisibility(View.GONE);
                                        }
                                        if (id == 20 || subid == 20) {
                                            flipAnimation20.reverse();
                                            binding.highMainCard20.startAnimation(flipAnimation20);
                                            binding.highEqualImpact20.setVisibility(View.GONE);
                                        }
                                        if (id == 21 || subid == 21) {
                                            flipAnimation21.reverse();
                                            binding.highMainCard21.startAnimation(flipAnimation21);
                                            binding.highEqualImpact21.setVisibility(View.GONE);
                                        }
                                        if (id == 22 || subid == 22) {
                                            flipAnimation22.reverse();
                                            binding.highMainCard22.startAnimation(flipAnimation22);
                                            binding.highEqualImpact22.setVisibility(View.GONE);
                                        }
                                        if (id == 23 || subid == 23) {
                                            flipAnimation23.reverse();
                                            binding.highMainCard23.startAnimation(flipAnimation23);
                                            binding.highEqualImpact23.setVisibility(View.GONE);
                                        }
                                        if (id == 24 || subid == 24) {
                                            flipAnimation24.reverse();
                                            binding.highMainCard24.startAnimation(flipAnimation24);
                                            binding.highEqualImpact24.setVisibility(View.GONE);
                                        }
                                        if (id == 25 || subid == 25) {
                                            flipAnimation25.reverse();
                                            binding.highMainCard25.startAnimation(flipAnimation25);
                                            binding.highEqualImpact25.setVisibility(View.GONE);
                                        }
                                        if (id == 26 || subid == 26) {
                                            flipAnimation26.reverse();
                                            binding.highMainCard26.startAnimation(flipAnimation26);
                                            binding.highEqualImpact26.setVisibility(View.GONE);
                                        }
                                        if (id == 27 || subid == 27) {
                                            flipAnimation27.reverse();
                                            binding.highMainCard27.startAnimation(flipAnimation27);
                                            binding.highEqualImpact27.setVisibility(View.GONE);
                                        }
                                        if (id == 28 || subid == 28) {
                                            flipAnimation28.reverse();
                                            binding.highMainCard28.startAnimation(flipAnimation28);
                                            binding.highEqualImpact28.setVisibility(View.GONE);
                                        }


                                        delay(200);
                                        Log.d(TAG, "sub" + subid);

                                    }
                                };
                                handler = new Handler(Looper.getMainLooper());
                                handler.postDelayed(runnable, 500);
                                Log.d(TAG, "else");
                            }
                        }
                    } else {
                        Log.d(TAG, "실패");
                    }
                }
            };

            binding.highCardFront1.setOnClickListener(click);
            binding.highCardFront2.setOnClickListener(click);
            binding.highCardFront3.setOnClickListener(click);
            binding.highCardFront4.setOnClickListener(click);
            binding.highCardFront5.setOnClickListener(click);
            binding.highCardFront6.setOnClickListener(click);
            binding.highCardFront7.setOnClickListener(click);
            binding.highCardFront8.setOnClickListener(click);
            binding.highCardFront9.setOnClickListener(click);
            binding.highCardFront10.setOnClickListener(click);
            binding.highCardFront11.setOnClickListener(click);
            binding.highCardFront12.setOnClickListener(click);
            binding.highCardFront13.setOnClickListener(click);
            binding.highCardFront14.setOnClickListener(click);
            binding.highCardFront15.setOnClickListener(click);
            binding.highCardFront16.setOnClickListener(click);
            binding.highCardFront17.setOnClickListener(click);
            binding.highCardFront18.setOnClickListener(click);
            binding.highCardFront19.setOnClickListener(click);
            binding.highCardFront20.setOnClickListener(click);
            binding.highCardFront21.setOnClickListener(click);
            binding.highCardFront22.setOnClickListener(click);
            binding.highCardFront23.setOnClickListener(click);
            binding.highCardFront24.setOnClickListener(click);
            binding.highCardFront25.setOnClickListener(click);
            binding.highCardFront26.setOnClickListener(click);
            binding.highCardFront27.setOnClickListener(click);
            binding.highCardFront28.setOnClickListener(click);
        }

        public void start() {    //시작했을때 카드를 바로 보여주는 메소드
            flipAnimation = new FlipAnimation(binding.highCardFront1, binding.highCardBack1);
            binding.highMainCard1.startAnimation(flipAnimation);

            flipAnimation2 = new FlipAnimation(binding.highCardFront2, binding.highCardBack2);
            binding.highMainCard2.startAnimation(flipAnimation2);

            flipAnimation3 = new FlipAnimation(binding.highCardFront3, binding.highCardBack3);
            binding.highMainCard3.startAnimation(flipAnimation3);

            flipAnimation4 = new FlipAnimation(binding.highCardFront4, binding.highCardBack4);
            binding.highMainCard4.startAnimation(flipAnimation4);

            flipAnimation5 = new FlipAnimation(binding.highCardFront5, binding.highCardBack5);
            binding.highMainCard5.startAnimation(flipAnimation5);

            flipAnimation6 = new FlipAnimation(binding.highCardFront6, binding.highCardBack6);
            binding.highMainCard6.startAnimation(flipAnimation6);

            flipAnimation7 = new FlipAnimation(binding.highCardFront7, binding.highCardBack7);
            binding.highMainCard7.startAnimation(flipAnimation7);

            flipAnimation8 = new FlipAnimation(binding.highCardFront8, binding.highCardBack8);
            binding.highMainCard8.startAnimation(flipAnimation8);

            flipAnimation9 = new FlipAnimation(binding.highCardFront9, binding.highCardBack9);
            binding.highMainCard9.startAnimation(flipAnimation9);

            flipAnimation10 = new FlipAnimation(binding.highCardFront10, binding.highCardBack10);
            binding.highMainCard10.startAnimation(flipAnimation10);

            flipAnimation11 = new FlipAnimation(binding.highCardFront11, binding.highCardBack11);
            binding.highMainCard11.startAnimation(flipAnimation11);

            flipAnimation12 = new FlipAnimation(binding.highCardFront12, binding.highCardBack12);
            binding.highMainCard12.startAnimation(flipAnimation12);

            flipAnimation13 = new FlipAnimation(binding.highCardFront13, binding.highCardBack13);
            binding.highMainCard13.startAnimation(flipAnimation13);

            flipAnimation14 = new FlipAnimation(binding.highCardFront14, binding.highCardBack14);
            binding.highMainCard14.startAnimation(flipAnimation14);

            flipAnimation15 = new FlipAnimation(binding.highCardFront15, binding.highCardBack15);
            binding.highMainCard15.startAnimation(flipAnimation15);

            flipAnimation16 = new FlipAnimation(binding.highCardFront16, binding.highCardBack16);
            binding.highMainCard16.startAnimation(flipAnimation16);

            flipAnimation17 = new FlipAnimation(binding.highCardFront17, binding.highCardBack17);
            binding.highMainCard17.startAnimation(flipAnimation17);

            flipAnimation18 = new FlipAnimation(binding.highCardFront18, binding.highCardBack18);
            binding.highMainCard18.startAnimation(flipAnimation18);

            flipAnimation19 = new FlipAnimation(binding.highCardFront19, binding.highCardBack19);
            binding.highMainCard19.startAnimation(flipAnimation19);

            flipAnimation20 = new FlipAnimation(binding.highCardFront20, binding.highCardBack20);
            binding.highMainCard20.startAnimation(flipAnimation20);

            flipAnimation21 = new FlipAnimation(binding.highCardFront21, binding.highCardBack21);
            binding.highMainCard21.startAnimation(flipAnimation21);

            flipAnimation22 = new FlipAnimation(binding.highCardFront22, binding.highCardBack22);
            binding.highMainCard22.startAnimation(flipAnimation22);

            flipAnimation23 = new FlipAnimation(binding.highCardFront23, binding.highCardBack23);
            binding.highMainCard23.startAnimation(flipAnimation23);

            flipAnimation24 = new FlipAnimation(binding.highCardFront24, binding.highCardBack24);
            binding.highMainCard24.startAnimation(flipAnimation24);

            flipAnimation25 = new FlipAnimation(binding.highCardFront25, binding.highCardBack25);
            binding.highMainCard25.startAnimation(flipAnimation25);

            flipAnimation26 = new FlipAnimation(binding.highCardFront26, binding.highCardBack26);
            binding.highMainCard26.startAnimation(flipAnimation26);

            flipAnimation27 = new FlipAnimation(binding.highCardFront27, binding.highCardBack27);
            binding.highMainCard27.startAnimation(flipAnimation27);

            flipAnimation28 = new FlipAnimation(binding.highCardFront28, binding.highCardBack28);
            binding.highMainCard28.startAnimation(flipAnimation28);
        }

        public void reverse() {    //start의 메소드가 실행된이후 3초후 카드를 뒤집는 메소드 실행
            flipAnimation.reverse();
            binding.highMainCard1.startAnimation(flipAnimation);

            flipAnimation2.reverse();
            binding.highMainCard2.startAnimation(flipAnimation2);

            flipAnimation3.reverse();
            binding.highMainCard3.startAnimation(flipAnimation3);

            flipAnimation4.reverse();
            binding.highMainCard4.startAnimation(flipAnimation4);

            flipAnimation5.reverse();
            binding.highMainCard5.startAnimation(flipAnimation5);

            flipAnimation6.reverse();
            binding.highMainCard6.startAnimation(flipAnimation6);

            flipAnimation7.reverse();
            binding.highMainCard7.startAnimation(flipAnimation7);

            flipAnimation8.reverse();
            binding.highMainCard8.startAnimation(flipAnimation8);

            flipAnimation9.reverse();
            binding.highMainCard9.startAnimation(flipAnimation9);

            flipAnimation10.reverse();
            binding.highMainCard10.startAnimation(flipAnimation10);

            flipAnimation11.reverse();
            binding.highMainCard11.startAnimation(flipAnimation11);

            flipAnimation12.reverse();
            binding.highMainCard12.startAnimation(flipAnimation12);

            flipAnimation13.reverse();
            binding.highMainCard13.startAnimation(flipAnimation13);

            flipAnimation14.reverse();
            binding.highMainCard14.startAnimation(flipAnimation14);

            flipAnimation15.reverse();
            binding.highMainCard15.startAnimation(flipAnimation15);

            flipAnimation16.reverse();
            binding.highMainCard16.startAnimation(flipAnimation16);

            flipAnimation17.reverse();
            binding.highMainCard17.startAnimation(flipAnimation17);

            flipAnimation18.reverse();
            binding.highMainCard18.startAnimation(flipAnimation18);

            flipAnimation19.reverse();
            binding.highMainCard19.startAnimation(flipAnimation19);

            flipAnimation20.reverse();
            binding.highMainCard20.startAnimation(flipAnimation20);

            flipAnimation21.reverse();
            binding.highMainCard21.startAnimation(flipAnimation21);

            flipAnimation22.reverse();
            binding.highMainCard22.startAnimation(flipAnimation22);

            flipAnimation23.reverse();
            binding.highMainCard23.startAnimation(flipAnimation23);

            flipAnimation24.reverse();
            binding.highMainCard24.startAnimation(flipAnimation24);

            flipAnimation25.reverse();
            binding.highMainCard25.startAnimation(flipAnimation25);

            flipAnimation26.reverse();
            binding.highMainCard26.startAnimation(flipAnimation26);

            flipAnimation27.reverse();
            binding.highMainCard27.startAnimation(flipAnimation27);

            flipAnimation28.reverse();
            binding.highMainCard28.startAnimation(flipAnimation28);
        }


        void flipCard(final int id) {     //id를 받아 어떤 아이디가 클릭됬는지를 확인해서 카드뒤집는 애니메이션을 실행하는 메소드

            if (id == 1) {
                flipAnimation = new FlipAnimation(binding.highCardFront1, binding.highCardBack1);
                binding.highMainCard1.startAnimation(flipAnimation);
            } else if (id == 2) {
                flipAnimation2 = new FlipAnimation(binding.highCardFront2, binding.highCardBack2);
                binding.highMainCard2.startAnimation(flipAnimation2);
            } else if (id == 3) {
                flipAnimation3 = new FlipAnimation(binding.highCardFront3, binding.highCardBack3);
                binding.highMainCard3.startAnimation(flipAnimation3);
            } else if (id == 4) {
                flipAnimation4 = new FlipAnimation(binding.highCardFront4, binding.highCardBack4);
                binding.highMainCard4.startAnimation(flipAnimation4);
            } else if (id == 5) {
                flipAnimation5 = new FlipAnimation(binding.highCardFront5, binding.highCardBack5);
                binding.highMainCard5.startAnimation(flipAnimation5);
            } else if (id == 6) {
                flipAnimation6 = new FlipAnimation(binding.highCardFront6, binding.highCardBack6);
                binding.highMainCard6.startAnimation(flipAnimation6);
            } else if (id == 7) {
                flipAnimation7 = new FlipAnimation(binding.highCardFront7, binding.highCardBack7);
                binding.highMainCard7.startAnimation(flipAnimation7);
            } else if (id == 8) {
                flipAnimation8 = new FlipAnimation(binding.highCardFront8, binding.highCardBack8);
                binding.highMainCard8.startAnimation(flipAnimation8);
            } else if (id == 9) {
                flipAnimation9 = new FlipAnimation(binding.highCardFront9, binding.highCardBack9);
                binding.highMainCard9.startAnimation(flipAnimation9);
            } else if (id == 10) {
                flipAnimation10 = new FlipAnimation(binding.highCardFront10, binding.highCardBack10);
                binding.highMainCard10.startAnimation(flipAnimation10);
            } else if (id == 11) {
                flipAnimation11 = new FlipAnimation(binding.highCardFront11, binding.highCardBack11);
                binding.highMainCard11.startAnimation(flipAnimation11);
            } else if (id == 12) {
                flipAnimation12 = new FlipAnimation(binding.highCardFront12, binding.highCardBack12);
                binding.highMainCard12.startAnimation(flipAnimation12);
            } else if (id == 13) {
                flipAnimation13 = new FlipAnimation(binding.highCardFront13, binding.highCardBack13);
                binding.highMainCard13.startAnimation(flipAnimation13);
            } else if (id == 14) {
                flipAnimation14 = new FlipAnimation(binding.highCardFront14, binding.highCardBack14);
                binding.highMainCard14.startAnimation(flipAnimation14);
            } else if (id == 15) {
                flipAnimation15 = new FlipAnimation(binding.highCardFront15, binding.highCardBack15);
                binding.highMainCard15.startAnimation(flipAnimation15);
            } else if (id == 16) {
                flipAnimation16 = new FlipAnimation(binding.highCardFront16, binding.highCardBack16);
                binding.highMainCard16.startAnimation(flipAnimation16);
            } else if (id == 17) {
                flipAnimation17 = new FlipAnimation(binding.highCardFront17, binding.highCardBack17);
                binding.highMainCard17.startAnimation(flipAnimation17);
            } else if (id == 18) {
                flipAnimation18 = new FlipAnimation(binding.highCardFront18, binding.highCardBack18);
                binding.highMainCard18.startAnimation(flipAnimation18);
            } else if (id == 19) {
                flipAnimation19 = new FlipAnimation(binding.highCardFront19, binding.highCardBack19);
                binding.highMainCard19.startAnimation(flipAnimation19);
            } else if (id == 20) {
                flipAnimation20 = new FlipAnimation(binding.highCardFront20, binding.highCardBack20);
                binding.highMainCard20.startAnimation(flipAnimation20);
            } else if (id == 21) {
                flipAnimation21 = new FlipAnimation(binding.highCardFront21, binding.highCardBack21);
                binding.highMainCard21.startAnimation(flipAnimation21);
            } else if (id == 22) {
                flipAnimation22 = new FlipAnimation(binding.highCardFront22, binding.highCardBack22);
                binding.highMainCard22.startAnimation(flipAnimation22);
            } else if (id == 23) {
                flipAnimation23 = new FlipAnimation(binding.highCardFront23, binding.highCardBack23);
                binding.highMainCard23.startAnimation(flipAnimation23);
            } else if (id == 24) {
                flipAnimation24 = new FlipAnimation(binding.highCardFront24, binding.highCardBack24);
                binding.highMainCard24.startAnimation(flipAnimation24);
            } else if (id == 25) {
                flipAnimation25 = new FlipAnimation(binding.highCardFront25, binding.highCardBack25);
                binding.highMainCard25.startAnimation(flipAnimation25);
            } else if (id == 26) {
                flipAnimation26 = new FlipAnimation(binding.highCardFront26, binding.highCardBack26);
                binding.highMainCard26.startAnimation(flipAnimation26);
            } else if (id == 27) {
                flipAnimation27 = new FlipAnimation(binding.highCardFront27, binding.highCardBack27);
                binding.highMainCard27.startAnimation(flipAnimation27);
            } else if (id == 28) {
                flipAnimation28 = new FlipAnimation(binding.highCardFront28, binding.highCardBack28);
                binding.highMainCard28.startAnimation(flipAnimation28);
            }
        }

        public void delay(long a) {           //빠르게 누르지 못하게 막는 타이머메소드
            timer2 = new CountDownTimer(a, 100) {
                @Override
                public void onTick(long millis) {
                }

                @Override
                public void onFinish() {
                    click(click);
                    clickcount = 0;
                    same = 0;
                    id = 0;
                    subid = 0;
                    Log.d("CardActivity", "timer delay");
                }
            };
            timer2.start();
        }

        public void click(View.OnClickListener click) {      //null값인 리스너에 다시 클릭리스너를 주는 메소드
            binding.highCardFront1.setOnClickListener(click);
            binding.highCardFront2.setOnClickListener(click);
            binding.highCardFront3.setOnClickListener(click);
            binding.highCardFront4.setOnClickListener(click);
            binding.highCardFront5.setOnClickListener(click);
            binding.highCardFront6.setOnClickListener(click);
            binding.highCardFront7.setOnClickListener(click);
            binding.highCardFront8.setOnClickListener(click);
            binding.highCardFront9.setOnClickListener(click);
            binding.highCardFront10.setOnClickListener(click);
            binding.highCardFront11.setOnClickListener(click);
            binding.highCardFront12.setOnClickListener(click);
            binding.highCardFront13.setOnClickListener(click);
            binding.highCardFront14.setOnClickListener(click);
            binding.highCardFront15.setOnClickListener(click);
            binding.highCardFront16.setOnClickListener(click);
            binding.highCardFront17.setOnClickListener(click);
            binding.highCardFront18.setOnClickListener(click);
            binding.highCardFront19.setOnClickListener(click);
            binding.highCardFront20.setOnClickListener(click);
            binding.highCardFront21.setOnClickListener(click);
            binding.highCardFront22.setOnClickListener(click);
            binding.highCardFront23.setOnClickListener(click);
            binding.highCardFront24.setOnClickListener(click);
            binding.highCardFront25.setOnClickListener(click);
            binding.highCardFront26.setOnClickListener(click);
            binding.highCardFront27.setOnClickListener(click);
            binding.highCardFront28.setOnClickListener(click);

        }

        void click2() {           //버튼리스너에 null값 주는 메소드
            binding.highCardFront1.setOnClickListener(null);
            binding.highCardFront2.setOnClickListener(null);
            binding.highCardFront3.setOnClickListener(null);
            binding.highCardFront4.setOnClickListener(null);
            binding.highCardFront5.setOnClickListener(null);
            binding.highCardFront6.setOnClickListener(null);
            binding.highCardFront7.setOnClickListener(null);
            binding.highCardFront8.setOnClickListener(null);
            binding.highCardFront9.setOnClickListener(null);
            binding.highCardFront10.setOnClickListener(null);
            binding.highCardFront11.setOnClickListener(null);
            binding.highCardFront12.setOnClickListener(null);
            binding.highCardFront13.setOnClickListener(null);
            binding.highCardFront14.setOnClickListener(null);
            binding.highCardFront15.setOnClickListener(null);
            binding.highCardFront16.setOnClickListener(null);
            binding.highCardFront17.setOnClickListener(null);
            binding.highCardFront18.setOnClickListener(null);
            binding.highCardFront19.setOnClickListener(null);
            binding.highCardFront20.setOnClickListener(null);
            binding.highCardFront21.setOnClickListener(null);
            binding.highCardFront22.setOnClickListener(null);
            binding.highCardFront23.setOnClickListener(null);
            binding.highCardFront24.setOnClickListener(null);
            binding.highCardFront25.setOnClickListener(null);
            binding.highCardFront26.setOnClickListener(null);
            binding.highCardFront27.setOnClickListener(null);
            binding.highCardFront28.setOnClickListener(null);
        }

    }


    public class A2ViewHolder extends CustomViewHolder {
        CarditemMediumBinding binding;

        A2ViewHolder(final CarditemMediumBinding binding, final Context context, final int nameNum) {
            super(binding);
            this.binding = binding;
            alpa = AnimationUtils.loadAnimation(context, R.anim.cardani);  //효과를 주는 애니메이션
            click =new CommonClickListener() {
                @Override
                public void performClick(View v) {

                    if (clickcount <= 2) {
                        clickcount++;
                        if (v == binding.mediumCardFront1) {
                            flipCard(1);
                            id = 1;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotMediumImgs[a[0]];
                                } else if (nameNum == 2) {
                                    sameImage = animalMediumImgs[a[0]];
                                } else if (nameNum == 3) {
                                    sameImage = flagMediumImgs[a[0]];
                                }
                            }
                        } else if (v == binding.mediumCardFront2) {
                            flipCard(2);
                            id = 2;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotMediumImgs[a[1]];
                                } else if (nameNum == 2) {
                                    sameImage = animalMediumImgs[a[1]];
                                } else if (nameNum == 3) {
                                    sameImage = flagMediumImgs[a[1]];
                                }
                            }
                        } else if (v == binding.mediumCardFront3) {
                            flipCard(3);
                            id = 3;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotMediumImgs[a[2]];
                                } else if (nameNum == 2) {
                                    sameImage = animalMediumImgs[a[2]];
                                } else if (nameNum == 3) {
                                    sameImage = flagMediumImgs[a[2]];
                                }
                            }
                        } else if (v == binding.mediumCardFront4) {
                            flipCard(4);
                            id = 4;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotMediumImgs[a[3]];
                                } else if (nameNum == 2) {
                                    sameImage = animalMediumImgs[a[3]];
                                } else if (nameNum == 3) {
                                    sameImage = flagMediumImgs[a[3]];
                                }
                            }
                        } else if (v == binding.mediumCardFront5) {
                            flipCard(5);
                            id = 5;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotMediumImgs[a[4]];
                                } else if (nameNum == 2) {
                                    sameImage = animalMediumImgs[a[4]];
                                } else if (nameNum == 3) {
                                    sameImage = flagMediumImgs[a[4]];
                                }
                            }
                        } else if (v == binding.mediumCardFront6) {
                            flipCard(6);
                            id = 6;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotMediumImgs[a[5]];
                                } else if (nameNum == 2) {
                                    sameImage = animalMediumImgs[a[5]];
                                } else if (nameNum == 3) {
                                    sameImage = flagMediumImgs[a[5]];
                                }
                            }
                        } else if (v == binding.mediumCardFront7) {
                            flipCard(7);
                            id = 7;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotMediumImgs[a[6]];
                                } else if (nameNum == 2) {
                                    sameImage = animalMediumImgs[a[6]];
                                } else if (nameNum == 3) {
                                    sameImage = flagMediumImgs[a[6]];
                                }
                            }
                        } else if (v == binding.mediumCardFront8) {
                            flipCard(8);
                            id = 8;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotMediumImgs[a[7]];
                                } else if (nameNum == 2) {
                                    sameImage = animalMediumImgs[a[7]];
                                } else if (nameNum == 3) {
                                    sameImage = flagMediumImgs[a[7]];
                                }
                            }
                        } else if (v == binding.mediumCardFront9) {
                            flipCard(9);
                            id = 9;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotMediumImgs[a[8]];
                                } else if (nameNum == 2) {
                                    sameImage = animalMediumImgs[a[8]];
                                } else if (nameNum == 3) {
                                    sameImage = flagMediumImgs[a[8]];
                                }
                            }
                        } else if (v == binding.mediumCardFront10) {
                            flipCard(10);
                            id = 10;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotMediumImgs[a[9]];
                                } else if (nameNum == 2) {
                                    sameImage = animalMediumImgs[a[9]];
                                } else if (nameNum == 3) {
                                    sameImage = flagMediumImgs[a[9]];
                                }
                            }
                        } else if (v == binding.mediumCardFront11) {
                            flipCard(11);
                            id = 11;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotMediumImgs[a[10]];
                                } else if (nameNum == 2) {
                                    sameImage = animalMediumImgs[a[10]];
                                } else if (nameNum == 3) {
                                    sameImage = flagMediumImgs[a[10]];
                                }
                            }
                        } else if (v == binding.mediumCardFront12) {
                            flipCard(12);
                            id = 12;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotMediumImgs[a[11]];
                                } else if (nameNum == 2) {
                                    sameImage = animalMediumImgs[a[11]];
                                } else if (nameNum == 3) {
                                    sameImage = flagMediumImgs[a[11]];
                                }
                            }
                        } else if (v == binding.mediumCardFront13) {
                            flipCard(13);
                            id = 13;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotMediumImgs[a[12]];
                                } else if (nameNum == 2) {
                                    sameImage = animalMediumImgs[a[12]];
                                } else if (nameNum == 3) {
                                    sameImage = flagMediumImgs[a[12]];
                                }
                            }
                        } else if (v == binding.mediumCardFront14) {
                            flipCard(14);
                            id = 14;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotMediumImgs[a[13]];
                                } else if (nameNum == 2) {
                                    sameImage = animalMediumImgs[a[13]];
                                } else if (nameNum == 3) {
                                    sameImage = flagMediumImgs[a[13]];
                                }
                            }
                        } else if (v == binding.mediumCardFront15) {
                            flipCard(15);
                            id = 15;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotMediumImgs[a[14]];
                                } else if (nameNum == 2) {
                                    sameImage = animalMediumImgs[a[14]];
                                } else if (nameNum == 3) {
                                    sameImage = flagMediumImgs[a[14]];
                                }
                            }
                        } else if (v == binding.mediumCardFront16) {
                            flipCard(16);
                            id = 16;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotMediumImgs[a[15]];
                                } else if (nameNum == 2) {
                                    sameImage = animalMediumImgs[a[15]];
                                } else if (nameNum == 3) {
                                    sameImage = flagMediumImgs[a[15]];
                                }
                            }
                        } else if (v == binding.mediumCardFront17) {
                            flipCard(17);
                            id = 17;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotMediumImgs[a[16]];
                                } else if (nameNum == 2) {
                                    sameImage = animalMediumImgs[a[16]];
                                } else if (nameNum == 3) {
                                    sameImage = flagMediumImgs[a[16]];
                                }
                            }
                        } else if (v == binding.mediumCardFront18) {
                            flipCard(18);
                            id = 18;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotMediumImgs[a[17]];
                                } else if (nameNum == 2) {
                                    sameImage = animalMediumImgs[a[17]];
                                } else if (nameNum == 3) {
                                    sameImage = flagMediumImgs[a[17]];
                                }
                            }
                        }
                        if (clickcount == 1) {
                            same = sameImage;
                            subid = id;
                            Log.d(TAG, "same : " + same);
                        }
                        if (clickcount == 2) {
                            click2();
                            if (sameImage == same) {         //첫번째 클릭과 두번째 클릭의 이미지값이 같으면 조건이 발동
                                runnable3 = new Runnable() {  //카드 2개 맞추고 사라지기
                                    @Override
                                    public void run() {              //조건이 맞는 값이 있으면 카드2개가 맞았다고 효과를 준다
                                        if ((sameImage == tarotMediumImgs[a[0]] && same == tarotMediumImgs[a[0]]) || (sameImage == animalMediumImgs[a[0]] && same == animalMediumImgs[a[0]]) || (sameImage == flagMediumImgs[a[0]] && same == flagMediumImgs[a[0]])) {
                                            binding.mediumEqualImpact1.setVisibility(View.VISIBLE);
                                            binding.mediumEqualImpact1.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotMediumImgs[a[1]] && same == tarotMediumImgs[a[1]]) || (sameImage == animalMediumImgs[a[1]] && same == animalMediumImgs[a[1]]) || (sameImage == flagMediumImgs[a[1]] && same == flagMediumImgs[a[1]])) {
                                            binding.mediumEqualImpact2.setVisibility(View.VISIBLE);
                                            binding.mediumEqualImpact2.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotMediumImgs[a[2]] && same == tarotMediumImgs[a[2]]) || (sameImage == animalMediumImgs[a[2]] && same == animalMediumImgs[a[2]]) || (sameImage == flagMediumImgs[a[2]] && same == flagMediumImgs[a[2]])) {
                                            binding.mediumEqualImpact3.setVisibility(View.VISIBLE);
                                            binding.mediumEqualImpact3.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotMediumImgs[a[3]] && same == tarotMediumImgs[a[3]]) || (sameImage == animalMediumImgs[a[3]] && same == animalMediumImgs[a[3]]) || (sameImage == flagMediumImgs[a[3]] && same == flagMediumImgs[a[3]])) {
                                            binding.mediumEqualImpact4.setVisibility(View.VISIBLE);
                                            binding.mediumEqualImpact4.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotMediumImgs[a[4]] && same == tarotMediumImgs[a[4]]) || (sameImage == animalMediumImgs[a[4]] && same == animalMediumImgs[a[4]]) || (sameImage == flagMediumImgs[a[4]] && same == flagMediumImgs[a[4]])) {
                                            binding.mediumEqualImpact5.setVisibility(View.VISIBLE);
                                            binding.mediumEqualImpact5.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotMediumImgs[a[5]] && same == tarotMediumImgs[a[5]]) || (sameImage == animalMediumImgs[a[5]] && same == animalMediumImgs[a[5]]) || (sameImage == flagMediumImgs[a[5]] && same == flagMediumImgs[a[5]])) {
                                            binding.mediumEqualImpact6.setVisibility(View.VISIBLE);
                                            binding.mediumEqualImpact6.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotMediumImgs[a[6]] && same == tarotMediumImgs[a[6]]) || (sameImage == animalMediumImgs[a[6]] && same == animalMediumImgs[a[6]]) || (sameImage == flagMediumImgs[a[6]] && same == flagMediumImgs[a[6]])) {
                                            binding.mediumEqualImpact7.setVisibility(View.VISIBLE);
                                            binding.mediumEqualImpact7.startAnimation(alpa);

                                        }
                                        if ((sameImage == tarotMediumImgs[a[7]] && same == tarotMediumImgs[a[7]]) || (sameImage == animalMediumImgs[a[7]] && same == animalMediumImgs[a[7]]) || (sameImage == flagMediumImgs[a[7]] && same == flagMediumImgs[a[7]])) {
                                            binding.mediumEqualImpact8.setVisibility(View.VISIBLE);
                                            binding.mediumEqualImpact8.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotMediumImgs[a[8]] && same == tarotMediumImgs[a[8]]) || (sameImage == animalMediumImgs[a[8]] && same == animalMediumImgs[a[8]]) || (sameImage == flagMediumImgs[a[8]] && same == flagMediumImgs[a[8]])) {
                                            binding.mediumEqualImpact9.setVisibility(View.VISIBLE);
                                            binding.mediumEqualImpact9.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotMediumImgs[a[9]] && same == tarotMediumImgs[a[9]]) || (sameImage == animalMediumImgs[a[9]] && same == animalMediumImgs[a[9]]) || (sameImage == flagMediumImgs[a[9]] && same == flagMediumImgs[a[9]])) {
                                            binding.mediumEqualImpact10.setVisibility(View.VISIBLE);
                                            binding.mediumEqualImpact10.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotMediumImgs[a[10]] && same == tarotMediumImgs[a[10]]) || (sameImage == animalMediumImgs[a[10]] && same == animalMediumImgs[a[10]]) || (sameImage == flagMediumImgs[a[10]] && same == flagMediumImgs[a[10]])) {
                                            binding.mediumEqualImpact11.setVisibility(View.VISIBLE);
                                            binding.mediumEqualImpact11.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotMediumImgs[a[11]] && same == tarotMediumImgs[a[11]]) || (sameImage == animalMediumImgs[a[11]] && same == animalMediumImgs[a[11]]) || (sameImage == flagMediumImgs[a[11]] && same == flagMediumImgs[a[11]])) {
                                            binding.mediumEqualImpact12.setVisibility(View.VISIBLE);
                                            binding.mediumEqualImpact12.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotMediumImgs[a[12]] && same == tarotMediumImgs[a[12]]) || (sameImage == animalMediumImgs[a[12]] && same == animalMediumImgs[a[12]]) || (sameImage == flagMediumImgs[a[12]] && same == flagMediumImgs[a[12]])) {
                                            binding.mediumEqualImpact13.setVisibility(View.VISIBLE);
                                            binding.mediumEqualImpact13.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotMediumImgs[a[13]] && same == tarotMediumImgs[a[13]]) || (sameImage == animalMediumImgs[a[13]] && same == animalMediumImgs[a[13]]) || (sameImage == flagMediumImgs[a[13]] && same == flagMediumImgs[a[13]])) {
                                            binding.mediumEqualImpact14.setVisibility(View.VISIBLE);
                                            binding.mediumEqualImpact14.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotMediumImgs[a[14]] && same == tarotMediumImgs[a[14]]) || (sameImage == animalMediumImgs[a[14]] && same == animalMediumImgs[a[14]]) || (sameImage == flagMediumImgs[a[14]] && same == flagMediumImgs[a[14]])) {
                                            binding.mediumEqualImpact15.setVisibility(View.VISIBLE);
                                            binding.mediumEqualImpact15.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotMediumImgs[a[15]] && same == tarotMediumImgs[a[15]]) || (sameImage == animalMediumImgs[a[15]] && same == animalMediumImgs[a[15]]) || (sameImage == flagMediumImgs[a[15]] && same == flagMediumImgs[a[15]])) {
                                            binding.mediumEqualImpact16.setVisibility(View.VISIBLE);
                                            binding.mediumEqualImpact16.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotMediumImgs[a[16]] && same == tarotMediumImgs[a[16]]) || (sameImage == animalMediumImgs[a[16]] && same == animalMediumImgs[a[16]]) || (sameImage == flagMediumImgs[a[16]] && same == flagMediumImgs[a[16]])) {
                                            binding.mediumEqualImpact17.setVisibility(View.VISIBLE);
                                            binding.mediumEqualImpact17.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotMediumImgs[a[17]] && same == tarotMediumImgs[a[17]]) || (sameImage == animalMediumImgs[a[17]] && same == animalMediumImgs[a[17]]) || (sameImage == flagMediumImgs[a[17]] && same == flagMediumImgs[a[17]])) {
                                            binding.mediumEqualImpact18.setVisibility(View.VISIBLE);
                                            binding.mediumEqualImpact18.startAnimation(alpa);
                                        }
                                        if (binding.mediumCardFront1.getVisibility() == View.GONE && binding.mediumCardFront2.getVisibility() == View.GONE && binding.mediumCardFront3.getVisibility() == View.GONE && binding.mediumCardFront4.getVisibility() == View.GONE && binding.mediumCardFront5.getVisibility() == View.GONE &&
                                                binding.mediumCardFront6.getVisibility() == View.GONE && binding.mediumCardFront7.getVisibility() == View.GONE && binding.mediumCardFront8.getVisibility() == View.GONE && binding.mediumCardFront9.getVisibility() == View.GONE && binding.mediumCardFront10.getVisibility() == View.GONE &&
                                                binding.mediumCardFront11.getVisibility() == View.GONE && binding.mediumCardFront12.getVisibility() == View.GONE && binding.mediumCardFront13.getVisibility() == View.GONE && binding.mediumCardFront14.getVisibility() == View.GONE && binding.mediumCardFront15.getVisibility() == View.GONE &&
                                                binding.mediumCardFront16.getVisibility() == View.GONE && binding.mediumCardFront17.getVisibility() == View.GONE && binding.mediumCardFront18.getVisibility() == View.GONE) {
                                            cardActivity = new CardActivity();
                                            cardActivity.message("끝",context);
                                            Log.d(TAG, "end2");
                                        }
                                        delay(200);
                                    }
                                };
                                handler = new Handler();
                                handler.postDelayed(runnable3, 500);
                                Log.d(TAG, "same");


                            } else {   //카드가 맞지않는 카드면은 다시 뒤집는다
                                click2();
                                Log.d(TAG, "run1");
                                runnable = new Runnable() {  //다시 뒤집기
                                    @Override
                                    public void run() {
                                        if (id == 1 || subid == 1) {
                                            flipAnimation.reverse();
                                            binding.mediumMainCard1.startAnimation(flipAnimation);
                                            binding.mediumEqualImpact1.setVisibility(View.GONE);
                                        }
                                        if (id == 2 || subid == 2) {
                                            flipAnimation2.reverse();
                                            binding.mediumMainCard2.startAnimation(flipAnimation2);
                                            binding.mediumEqualImpact2.setVisibility(View.GONE);
                                        }
                                        if (id == 3 || subid == 3) {
                                            flipAnimation3.reverse();
                                            binding.mediumMainCard3.startAnimation(flipAnimation3);
                                            binding.mediumEqualImpact3.setVisibility(View.GONE);
                                        }
                                        if (id == 4 || subid == 4) {
                                            flipAnimation4.reverse();
                                            binding.mediumMainCard4.startAnimation(flipAnimation4);
                                            binding.mediumEqualImpact4.setVisibility(View.GONE);
                                        }
                                        if (id == 5 || subid == 5) {
                                            flipAnimation5.reverse();
                                            binding.mediumMainCard5.startAnimation(flipAnimation5);
                                            binding.mediumEqualImpact5.setVisibility(View.GONE);
                                        }
                                        if (id == 6 || subid == 6) {
                                            flipAnimation6.reverse();
                                            binding.mediumMainCard6.startAnimation(flipAnimation6);
                                            binding.mediumEqualImpact6.setVisibility(View.GONE);
                                        }
                                        if (id == 7 || subid == 7) {
                                            flipAnimation7.reverse();
                                            binding.mediumMainCard7.startAnimation(flipAnimation7);
                                            binding.mediumEqualImpact7.setVisibility(View.GONE);

                                        }
                                        if (id == 8 || subid == 8) {
                                            flipAnimation8.reverse();
                                            binding.mediumMainCard8.startAnimation(flipAnimation8);
                                            binding.mediumEqualImpact8.setVisibility(View.GONE);
                                        }
                                        if (id == 9 || subid == 9) {
                                            flipAnimation9.reverse();
                                            binding.mediumMainCard9.startAnimation(flipAnimation9);
                                            binding.mediumEqualImpact9.setVisibility(View.GONE);
                                        }
                                        if (id == 10 || subid == 10) {
                                            flipAnimation10.reverse();
                                            binding.mediumMainCard10.startAnimation(flipAnimation10);
                                            binding.mediumEqualImpact10.setVisibility(View.GONE);
                                        }
                                        if (id == 11 || subid == 11) {
                                            flipAnimation11.reverse();
                                            binding.mediumMainCard11.startAnimation(flipAnimation11);
                                            binding.mediumEqualImpact11.setVisibility(View.GONE);
                                        }
                                        if (id == 12 || subid == 12) {
                                            flipAnimation12.reverse();
                                            binding.mediumMainCard12.startAnimation(flipAnimation12);
                                            binding.mediumEqualImpact12.setVisibility(View.GONE);
                                        }
                                        if (id == 13 || subid == 13) {
                                            flipAnimation13.reverse();
                                            binding.mediumMainCard13.startAnimation(flipAnimation13);
                                            binding.mediumEqualImpact13.setVisibility(View.GONE);
                                        }
                                        if (id == 14 || subid == 14) {
                                            flipAnimation14.reverse();
                                            binding.mediumMainCard14.startAnimation(flipAnimation14);
                                            binding.mediumEqualImpact14.setVisibility(View.GONE);
                                        }
                                        if (id == 15 || subid == 15) {
                                            flipAnimation15.reverse();
                                            binding.mediumMainCard15.startAnimation(flipAnimation15);
                                            binding.mediumEqualImpact15.setVisibility(View.GONE);
                                        }
                                        if (id == 16 || subid == 16) {
                                            flipAnimation16.reverse();
                                            binding.mediumMainCard16.startAnimation(flipAnimation16);
                                            binding.mediumEqualImpact16.setVisibility(View.GONE);
                                        }
                                        if (id == 17 || subid == 17) {
                                            flipAnimation17.reverse();
                                            binding.mediumMainCard17.startAnimation(flipAnimation17);
                                            binding.mediumEqualImpact17.setVisibility(View.GONE);
                                        }
                                        if (id == 18 || subid == 18) {
                                            flipAnimation18.reverse();
                                            binding.mediumMainCard18.startAnimation(flipAnimation18);
                                            binding.mediumEqualImpact18.setVisibility(View.GONE);
                                        }

                                        delay(200);

                                    }
                                };
                                handler = new Handler(Looper.getMainLooper());
                                handler.postDelayed(runnable, 500);
                                Log.d(TAG, "else");
                            }
                        }
                    } else {
                        Log.d(TAG, "실패2");
                    }
                }
            };
            binding.mediumCardFront1.setOnClickListener(click);
            binding.mediumCardFront2.setOnClickListener(click);
            binding.mediumCardFront3.setOnClickListener(click);
            binding.mediumCardFront4.setOnClickListener(click);
            binding.mediumCardFront5.setOnClickListener(click);
            binding.mediumCardFront6.setOnClickListener(click);
            binding.mediumCardFront7.setOnClickListener(click);
            binding.mediumCardFront8.setOnClickListener(click);
            binding.mediumCardFront9.setOnClickListener(click);
            binding.mediumCardFront10.setOnClickListener(click);
            binding.mediumCardFront11.setOnClickListener(click);
            binding.mediumCardFront12.setOnClickListener(click);
            binding.mediumCardFront13.setOnClickListener(click);
            binding.mediumCardFront14.setOnClickListener(click);
            binding.mediumCardFront15.setOnClickListener(click);
            binding.mediumCardFront16.setOnClickListener(click);
            binding.mediumCardFront17.setOnClickListener(click);
            binding.mediumCardFront18.setOnClickListener(click);
        }

        public void start() {         //시작했을때 카드를 바로 보여주는 메소드
            flipAnimation = new FlipAnimation(binding.mediumCardFront1, binding.mediumCardBack1);
            binding.mediumMainCard1.startAnimation(flipAnimation);

            flipAnimation2 = new FlipAnimation(binding.mediumCardFront2, binding.mediumCardBack2);
            binding.mediumMainCard2.startAnimation(flipAnimation2);

            flipAnimation3 = new FlipAnimation(binding.mediumCardFront3, binding.mediumCardBack3);
            binding.mediumMainCard3.startAnimation(flipAnimation3);

            flipAnimation4 = new FlipAnimation(binding.mediumCardFront4, binding.mediumCardBack4);
            binding.mediumMainCard4.startAnimation(flipAnimation4);

            flipAnimation5 = new FlipAnimation(binding.mediumCardFront5, binding.mediumCardBack5);
            binding.mediumMainCard5.startAnimation(flipAnimation5);

            flipAnimation6 = new FlipAnimation(binding.mediumCardFront6, binding.mediumCardBack6);
            binding.mediumMainCard6.startAnimation(flipAnimation6);

            flipAnimation7 = new FlipAnimation(binding.mediumCardFront7, binding.mediumCardBack7);
            binding.mediumMainCard7.startAnimation(flipAnimation7);

            flipAnimation8 = new FlipAnimation(binding.mediumCardFront8, binding.mediumCardBack8);
            binding.mediumMainCard8.startAnimation(flipAnimation8);

            flipAnimation9 = new FlipAnimation(binding.mediumCardFront9, binding.mediumCardBack9);
            binding.mediumMainCard9.startAnimation(flipAnimation9);

            flipAnimation10 = new FlipAnimation(binding.mediumCardFront10, binding.mediumCardBack10);
            binding.mediumMainCard10.startAnimation(flipAnimation10);

            flipAnimation11 = new FlipAnimation(binding.mediumCardFront11, binding.mediumCardBack11);
            binding.mediumMainCard11.startAnimation(flipAnimation11);

            flipAnimation12 = new FlipAnimation(binding.mediumCardFront12, binding.mediumCardBack12);
            binding.mediumMainCard12.startAnimation(flipAnimation12);

            flipAnimation13 = new FlipAnimation(binding.mediumCardFront13, binding.mediumCardBack13);
            binding.mediumMainCard13.startAnimation(flipAnimation13);

            flipAnimation14 = new FlipAnimation(binding.mediumCardFront14, binding.mediumCardBack14);
            binding.mediumMainCard14.startAnimation(flipAnimation14);

            flipAnimation15 = new FlipAnimation(binding.mediumCardFront15, binding.mediumCardBack15);
            binding.mediumMainCard15.startAnimation(flipAnimation15);

            flipAnimation16 = new FlipAnimation(binding.mediumCardFront16, binding.mediumCardBack16);
            binding.mediumMainCard16.startAnimation(flipAnimation16);

            flipAnimation17 = new FlipAnimation(binding.mediumCardFront17, binding.mediumCardBack17);
            binding.mediumMainCard17.startAnimation(flipAnimation17);

            flipAnimation18 = new FlipAnimation(binding.mediumCardFront18, binding.mediumCardBack18);
            binding.mediumMainCard18.startAnimation(flipAnimation18);
        }

        public void reverse() {        //   start의 메소드가 실행된이후 3초후 카드를 뒤집는 메소드 실행
            flipAnimation.reverse();
            binding.mediumMainCard1.startAnimation(flipAnimation);

            flipAnimation2.reverse();
            binding.mediumMainCard2.startAnimation(flipAnimation2);

            flipAnimation3.reverse();
            binding.mediumMainCard3.startAnimation(flipAnimation3);

            flipAnimation4.reverse();
            binding.mediumMainCard4.startAnimation(flipAnimation4);

            flipAnimation5.reverse();
            binding.mediumMainCard5.startAnimation(flipAnimation5);

            flipAnimation6.reverse();
            binding.mediumMainCard6.startAnimation(flipAnimation6);

            flipAnimation7.reverse();
            binding.mediumMainCard7.startAnimation(flipAnimation7);

            flipAnimation8.reverse();
            binding.mediumMainCard8.startAnimation(flipAnimation8);

            flipAnimation9.reverse();
            binding.mediumMainCard9.startAnimation(flipAnimation9);

            flipAnimation10.reverse();
            binding.mediumMainCard10.startAnimation(flipAnimation10);

            flipAnimation11.reverse();
            binding.mediumMainCard11.startAnimation(flipAnimation11);

            flipAnimation12.reverse();
            binding.mediumMainCard12.startAnimation(flipAnimation12);

            flipAnimation13.reverse();
            binding.mediumMainCard13.startAnimation(flipAnimation13);

            flipAnimation14.reverse();
            binding.mediumMainCard14.startAnimation(flipAnimation14);

            flipAnimation15.reverse();
            binding.mediumMainCard15.startAnimation(flipAnimation15);

            flipAnimation16.reverse();
            binding.mediumMainCard16.startAnimation(flipAnimation16);

            flipAnimation17.reverse();
            binding.mediumMainCard17.startAnimation(flipAnimation17);

            flipAnimation18.reverse();
            binding.mediumMainCard18.startAnimation(flipAnimation18);
        }

        void flipCard(final int id) {       //id를 받아 어떤 아이디가 클릭됬는지를 확인해서 카드뒤집는 애니메이션을 실행하는 메소드

            if (id == 1) {
                flipAnimation = new FlipAnimation(binding.mediumCardFront1, binding.mediumCardBack1);
                binding.mediumMainCard1.startAnimation(flipAnimation);
            } else if (id == 2) {
                flipAnimation2 = new FlipAnimation(binding.mediumCardFront2, binding.mediumCardBack2);
                binding.mediumMainCard2.startAnimation(flipAnimation2);

            } else if (id == 3) {
                flipAnimation3 = new FlipAnimation(binding.mediumCardFront3, binding.mediumCardBack3);
                binding.mediumMainCard3.startAnimation(flipAnimation3);

            } else if (id == 4) {
                flipAnimation4 = new FlipAnimation(binding.mediumCardFront4, binding.mediumCardBack4);
                binding.mediumMainCard4.startAnimation(flipAnimation4);

            } else if (id == 5) {
                flipAnimation5 = new FlipAnimation(binding.mediumCardFront5, binding.mediumCardBack5);
                binding.mediumMainCard5.startAnimation(flipAnimation5);
            } else if (id == 6) {
                flipAnimation6 = new FlipAnimation(binding.mediumCardFront6, binding.mediumCardBack6);
                binding.mediumMainCard6.startAnimation(flipAnimation6);
            } else if (id == 7) {
                flipAnimation7 = new FlipAnimation(binding.mediumCardFront7, binding.mediumCardBack7);
                binding.mediumMainCard7.startAnimation(flipAnimation7);
            } else if (id == 8) {
                flipAnimation8 = new FlipAnimation(binding.mediumCardFront8, binding.mediumCardBack8);
                binding.mediumMainCard8.startAnimation(flipAnimation8);
            } else if (id == 9) {
                flipAnimation9 = new FlipAnimation(binding.mediumCardFront9, binding.mediumCardBack9);
                binding.mediumMainCard9.startAnimation(flipAnimation9);
            } else if (id == 10) {
                flipAnimation10 = new FlipAnimation(binding.mediumCardFront10, binding.mediumCardBack10);
                binding.mediumMainCard10.startAnimation(flipAnimation10);
            } else if (id == 11) {
                flipAnimation11 = new FlipAnimation(binding.mediumCardFront11, binding.mediumCardBack11);
                binding.mediumMainCard11.startAnimation(flipAnimation11);
            } else if (id == 12) {
                flipAnimation12 = new FlipAnimation(binding.mediumCardFront12, binding.mediumCardBack12);
                binding.mediumMainCard12.startAnimation(flipAnimation12);
            } else if (id == 13) {
                flipAnimation13 = new FlipAnimation(binding.mediumCardFront13, binding.mediumCardBack13);
                binding.mediumMainCard13.startAnimation(flipAnimation13);
            } else if (id == 14) {
                flipAnimation14 = new FlipAnimation(binding.mediumCardFront14, binding.mediumCardBack14);
                binding.mediumMainCard14.startAnimation(flipAnimation14);
            } else if (id == 15) {
                flipAnimation15 = new FlipAnimation(binding.mediumCardFront15, binding.mediumCardBack15);
                binding.mediumMainCard15.startAnimation(flipAnimation15);
            } else if (id == 16) {
                flipAnimation16 = new FlipAnimation(binding.mediumCardFront16, binding.mediumCardBack16);
                binding.mediumMainCard16.startAnimation(flipAnimation16);
            } else if (id == 17) {
                flipAnimation17 = new FlipAnimation(binding.mediumCardFront17, binding.mediumCardBack17);
                binding.mediumMainCard17.startAnimation(flipAnimation17);
            } else if (id == 18) {
                flipAnimation18 = new FlipAnimation(binding.mediumCardFront18, binding.mediumCardBack18);
                binding.mediumMainCard18.startAnimation(flipAnimation18);
            }
        }

        public void delay(long a) {          //빠르게 누르지 못하게 막는 타이머메소드
            timer2 = new CountDownTimer(a, 100) {
                @Override
                public void onTick(long millis) {
                }

                @Override
                public void onFinish() {
                    click(click);
                    clickcount = 0;
                    same = 0;
                    id = 0;
                    subid = 0;
                    Log.d("CardActivity", "timer delay");
                }
            };
            timer2.start();
        }

        public void click(View.OnClickListener click) {          //null값인 리스너에 다시 클릭리스너를 주는 메소드
            binding.mediumCardFront1.setOnClickListener(click);
            binding.mediumCardFront2.setOnClickListener(click);
            binding.mediumCardFront3.setOnClickListener(click);
            binding.mediumCardFront4.setOnClickListener(click);
            binding.mediumCardFront5.setOnClickListener(click);
            binding.mediumCardFront6.setOnClickListener(click);
            binding.mediumCardFront7.setOnClickListener(click);
            binding.mediumCardFront8.setOnClickListener(click);
            binding.mediumCardFront9.setOnClickListener(click);
            binding.mediumCardFront10.setOnClickListener(click);
            binding.mediumCardFront11.setOnClickListener(click);
            binding.mediumCardFront12.setOnClickListener(click);
            binding.mediumCardFront13.setOnClickListener(click);
            binding.mediumCardFront14.setOnClickListener(click);
            binding.mediumCardFront15.setOnClickListener(click);
            binding.mediumCardFront16.setOnClickListener(click);
            binding.mediumCardFront17.setOnClickListener(click);
            binding.mediumCardFront18.setOnClickListener(click);

        }

        void click2() {   //버튼리스너에 null값 주는 메소드
            binding.mediumCardFront1.setOnClickListener(null);
            binding.mediumCardFront2.setOnClickListener(null);
            binding.mediumCardFront3.setOnClickListener(null);
            binding.mediumCardFront4.setOnClickListener(null);
            binding.mediumCardFront5.setOnClickListener(null);
            binding.mediumCardFront6.setOnClickListener(null);
            binding.mediumCardFront7.setOnClickListener(null);
            binding.mediumCardFront8.setOnClickListener(null);
            binding.mediumCardFront9.setOnClickListener(null);
            binding.mediumCardFront10.setOnClickListener(null);
            binding.mediumCardFront11.setOnClickListener(null);
            binding.mediumCardFront12.setOnClickListener(null);
            binding.mediumCardFront13.setOnClickListener(null);
            binding.mediumCardFront14.setOnClickListener(null);
            binding.mediumCardFront15.setOnClickListener(null);
            binding.mediumCardFront16.setOnClickListener(null);
            binding.mediumCardFront17.setOnClickListener(null);
            binding.mediumCardFront18.setOnClickListener(null);
        }
    }


    public class A3ViewHolder extends CustomViewHolder {
        CarditemLowBinding binding;


        A3ViewHolder(final CarditemLowBinding binding, final Context context, final int nameNum) {
            super(binding);
            this.binding = binding;
            alpa = AnimationUtils.loadAnimation(context, R.anim.cardani);   //효과 애니메이션

            click = new CommonClickListener() {
                @Override
                public void performClick(View v) {

                    if (clickcount <= 2) {
                        clickcount++;
                        if (v == binding.lowCardFront1) {
                            flipCard(1);
                            id = 1;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotLowImgs[a[0]];
                                } else if (nameNum == 2) {
                                    sameImage = animalLowImgs[a[0]];
                                } else if (nameNum == 3) {
                                    sameImage = flagLowImgs[a[0]];
                                }
                            }
                        } else if (v == binding.lowCardFront2) {
                            flipCard(2);
                            id = 2;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotLowImgs[a[1]];
                                } else if (nameNum == 2) {
                                    sameImage = animalLowImgs[a[1]];
                                } else if (nameNum == 3) {
                                    sameImage = flagLowImgs[a[1]];
                                }
                            }
                        } else if (v == binding.lowCardFront3) {
                            flipCard(3);
                            id = 3;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotLowImgs[a[2]];
                                } else if (nameNum == 2) {
                                    sameImage = animalLowImgs[a[2]];
                                } else if (nameNum == 3) {
                                    sameImage = flagLowImgs[a[2]];
                                }
                            }
                        } else if (v == binding.lowCardFront4) {
                            flipCard(4);
                            id = 4;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotLowImgs[a[3]];
                                } else if (nameNum == 2) {
                                    sameImage = animalLowImgs[a[3]];
                                } else if (nameNum == 3) {
                                    sameImage = flagLowImgs[a[3]];
                                }
                            }
                        } else if (v == binding.lowCardFront5) {
                            flipCard(5);
                            id = 5;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotLowImgs[a[4]];
                                } else if (nameNum == 2) {
                                    sameImage = animalLowImgs[a[4]];
                                } else if (nameNum == 3) {
                                    sameImage = flagLowImgs[a[4]];
                                }
                            }
                        } else if (v == binding.lowCardFront6) {
                            flipCard(6);
                            id = 6;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotLowImgs[a[5]];
                                } else if (nameNum == 2) {
                                    sameImage = animalLowImgs[a[5]];
                                } else if (nameNum == 3) {
                                    sameImage = flagLowImgs[a[5]];
                                }
                            }
                        } else if (v == binding.lowCardFront7) {
                            flipCard(7);
                            id = 7;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotLowImgs[a[6]];
                                } else if (nameNum == 2) {
                                    sameImage = animalLowImgs[a[6]];
                                } else if (nameNum == 3) {
                                    sameImage = flagLowImgs[a[6]];
                                }
                            }
                        } else if (v == binding.lowCardFront8) {
                            flipCard(8);
                            id = 8;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotLowImgs[a[7]];
                                } else if (nameNum == 2) {
                                    sameImage = animalLowImgs[a[7]];
                                } else if (nameNum == 3) {
                                    sameImage = flagLowImgs[a[7]];
                                }
                            }
                        } else if (v == binding.lowCardFront9) {
                            flipCard(9);
                            id = 9;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotLowImgs[a[8]];
                                } else if (nameNum == 2) {
                                    sameImage = animalLowImgs[a[8]];
                                } else if (nameNum == 3) {
                                    sameImage = flagLowImgs[a[8]];
                                }
                            }
                        } else if (v == binding.lowCardFront10) {
                            flipCard(10);
                            id = 10;
                            if (nameNum != 0) {
                                if (nameNum == 1) {
                                    sameImage = tarotLowImgs[a[9]];
                                } else if (nameNum == 2) {
                                    sameImage = animalLowImgs[a[9]];
                                } else if (nameNum == 3) {
                                    sameImage = flagLowImgs[a[9]];
                                }
                            }
                        }
                        if (clickcount == 1) {
                            same = sameImage;
                            subid = id;
                            Log.d(TAG, "same : " + same);
                        }
                        if (clickcount == 2) {
                            if (sameImage == same) {       //첫번째 클릭과 두번째 클릭의 이미지값이 같으면 조건이 발동
                                click2();
                                runnable3 = new Runnable() {  //카드 2개 맞추고 사라지기
                                    @Override
                                    public void run() {   //조건이 맞는 값이 있으면 카드2개가 맞았다고 효과를 준다
                                        if ((sameImage == tarotLowImgs[a[0]] || same == tarotLowImgs[a[0]]) || (sameImage == animalLowImgs[a[0]] || same == animalLowImgs[a[0]]) || (sameImage == flagLowImgs[a[0]] || same == flagLowImgs[a[0]])) {
                                            binding.lowEqualImpact1.setVisibility(View.VISIBLE);
                                            binding.lowEqualImpact1.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotLowImgs[a[1]] || same == tarotLowImgs[a[1]]) || (sameImage == animalLowImgs[a[1]] || same == animalLowImgs[a[1]]) || (sameImage == flagLowImgs[a[1]] || same == flagLowImgs[a[1]])) {
                                            binding.lowEqualImpact2.setVisibility(View.VISIBLE);
                                            binding.lowEqualImpact2.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotLowImgs[a[2]] || same == tarotLowImgs[a[2]]) || (sameImage == animalLowImgs[a[2]] || same == animalLowImgs[a[2]]) || (sameImage == flagLowImgs[a[2]] || same == flagLowImgs[a[2]])) {
                                            binding.lowEqualImpact3.setVisibility(View.VISIBLE);
                                            binding.lowEqualImpact3.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotLowImgs[a[3]] || same == tarotLowImgs[a[3]]) || (sameImage == animalLowImgs[a[3]] || same == animalLowImgs[a[3]]) || (sameImage == flagLowImgs[a[3]] || same == flagLowImgs[a[3]])) {
                                            binding.lowEqualImpact4.setVisibility(View.VISIBLE);
                                            binding.lowEqualImpact4.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotLowImgs[a[4]] || same == tarotLowImgs[a[4]]) || (sameImage == animalLowImgs[a[4]] || same == animalLowImgs[a[4]]) || (sameImage == flagLowImgs[a[4]] || same == flagLowImgs[a[4]])) {
                                            binding.lowEqualImpact5.setVisibility(View.VISIBLE);
                                            binding.lowEqualImpact5.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotLowImgs[a[5]] || same == tarotLowImgs[a[5]]) || (sameImage == animalLowImgs[a[5]] || same == animalLowImgs[a[5]]) || (sameImage == flagLowImgs[a[5]] || same == flagLowImgs[a[5]])) {
                                            binding.lowEqualImpact6.setVisibility(View.VISIBLE);
                                            binding.lowEqualImpact6.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotLowImgs[a[6]] || same == tarotLowImgs[a[6]]) || (sameImage == animalLowImgs[a[6]] || same == animalLowImgs[a[6]]) || (sameImage == flagLowImgs[a[6]] || same == flagLowImgs[a[6]])) {
                                            binding.lowEqualImpact7.setVisibility(View.VISIBLE);
                                            binding.lowEqualImpact7.startAnimation(alpa);

                                        }
                                        if ((sameImage == tarotLowImgs[a[7]] || same == tarotLowImgs[a[7]]) || (sameImage == animalLowImgs[a[7]] || same == animalLowImgs[a[7]]) || (sameImage == flagLowImgs[a[7]] || same == flagLowImgs[a[7]])) {
                                            binding.lowEqualImpact8.setVisibility(View.VISIBLE);
                                            binding.lowEqualImpact8.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotLowImgs[a[8]] || same == tarotLowImgs[a[8]]) || (sameImage == animalLowImgs[a[8]] || same == animalLowImgs[a[8]]) || (sameImage == flagLowImgs[a[8]] || same == flagLowImgs[a[8]])) {
                                            binding.lowEqualImpact9.setVisibility(View.VISIBLE);
                                            binding.lowEqualImpact9.startAnimation(alpa);
                                        }
                                        if ((sameImage == tarotLowImgs[a[9]] || same == tarotLowImgs[a[9]]) || (sameImage == animalLowImgs[a[9]] || same == animalLowImgs[a[9]]) || (sameImage == flagLowImgs[a[9]] || same == flagLowImgs[a[9]])) {
                                            binding.lowEqualImpact10.setVisibility(View.VISIBLE);
                                            binding.lowEqualImpact10.startAnimation(alpa);
                                        }

                                        if (binding.lowCardFront1.getVisibility() == View.GONE && binding.lowCardFront2.getVisibility() == View.GONE && binding.lowCardFront3.getVisibility() == View.GONE && binding.lowCardFront4.getVisibility() == View.GONE &&
                                                binding.lowCardFront5.getVisibility() == View.GONE && binding.lowCardFront6.getVisibility() == View.GONE && binding.lowCardFront7.getVisibility() == View.GONE && binding.lowCardFront8.getVisibility() == View.GONE &&
                                                binding.lowCardFront9.getVisibility() == View.GONE && binding.lowCardFront10.getVisibility() == View.GONE) {            //카드들이 모두 앞면이면 끝나는 조건
                                            cardActivity = new CardActivity();
                                            cardActivity.message("끝",context);
                                            Log.d(TAG, "end3");
                                            timer2.cancel();
                                        }
                                        delay(200);
                                        Log.d(TAG, "sub" + subid);

                                    }
                                };
                                handler = new Handler(Looper.getMainLooper());
                                handler.postDelayed(runnable3, 500);
                                Log.d(TAG, "same");


                            } else {                        //카드가 맞지않는 카드면은 다시 뒤집는다
                                click2();
                                runnable = new Runnable() {  //다시 뒤집기
                                    @Override
                                    public void run() {
                                        if (id == 1 || subid == 1) {
                                            flipAnimation.reverse();
                                            binding.lowMainCard1.startAnimation(flipAnimation);
                                            binding.lowEqualImpact1.setVisibility(View.GONE);
                                        }
                                        if (id == 2 || subid == 2) {
                                            flipAnimation2.reverse();
                                            binding.lowMainCard2.startAnimation(flipAnimation2);
                                            binding.lowEqualImpact2.setVisibility(View.GONE);
                                        }
                                        if (id == 3 || subid == 3) {
                                            flipAnimation3.reverse();
                                            binding.lowMainCard3.startAnimation(flipAnimation3);
                                            binding.lowEqualImpact3.setVisibility(View.GONE);
                                        }
                                        if (id == 4 || subid == 4) {
                                            flipAnimation4.reverse();
                                            binding.lowMainCard4.startAnimation(flipAnimation4);
                                            binding.lowEqualImpact4.setVisibility(View.GONE);
                                        }
                                        if (id == 5 || subid == 5) {
                                            flipAnimation5.reverse();
                                            binding.lowMainCard5.startAnimation(flipAnimation5);
                                            binding.lowEqualImpact5.setVisibility(View.GONE);
                                        }
                                        if (id == 6 || subid == 6) {
                                            flipAnimation6.reverse();
                                            binding.lowMainCard6.startAnimation(flipAnimation6);
                                            binding.lowEqualImpact6.setVisibility(View.GONE);
                                        }
                                        if (id == 7 || subid == 7) {
                                            flipAnimation7.reverse();
                                            binding.lowMainCard7.startAnimation(flipAnimation7);
                                            binding.lowEqualImpact7.setVisibility(View.GONE);

                                        }
                                        if (id == 8 || subid == 8) {
                                            flipAnimation8.reverse();
                                            binding.lowMainCard8.startAnimation(flipAnimation8);
                                            binding.lowEqualImpact8.setVisibility(View.GONE);
                                        }
                                        if (id == 9 || subid == 9) {
                                            flipAnimation9.reverse();
                                            binding.lowMainCard9.startAnimation(flipAnimation9);
                                            binding.lowEqualImpact9.setVisibility(View.GONE);
                                        }
                                        if (id == 10 || subid == 10) {
                                            flipAnimation10.reverse();
                                            binding.lowMainCard10.startAnimation(flipAnimation10);
                                            binding.lowEqualImpact10.setVisibility(View.GONE);
                                        }

                                        delay(200);
                                        Log.d(TAG, "sub" + subid);

                                    }
                                };
                                handler = new Handler(Looper.getMainLooper());
                                handler.postDelayed(runnable, 500);
                                Log.d(TAG, "else");


                            }
                        }
                    } else {
                        Log.d(TAG, "실패3");
                    }
                }
            };
            binding.lowCardFront1.setOnClickListener(click);
            binding.lowCardFront2.setOnClickListener(click);
            binding.lowCardFront3.setOnClickListener(click);
            binding.lowCardFront4.setOnClickListener(click);
            binding.lowCardFront5.setOnClickListener(click);
            binding.lowCardFront6.setOnClickListener(click);
            binding.lowCardFront7.setOnClickListener(click);
            binding.lowCardFront8.setOnClickListener(click);
            binding.lowCardFront9.setOnClickListener(click);
            binding.lowCardFront10.setOnClickListener(click);
        }

        public void click(View.OnClickListener click) {        //null값인 리스너에 다시 클릭리스너를 주는 메소드
            binding.lowCardFront1.setOnClickListener(click);
            binding.lowCardFront2.setOnClickListener(click);
            binding.lowCardFront3.setOnClickListener(click);
            binding.lowCardFront4.setOnClickListener(click);
            binding.lowCardFront5.setOnClickListener(click);
            binding.lowCardFront6.setOnClickListener(click);
            binding.lowCardFront7.setOnClickListener(click);
            binding.lowCardFront8.setOnClickListener(click);
            binding.lowCardFront9.setOnClickListener(click);
            binding.lowCardFront10.setOnClickListener(click);
        }

        void click2() {               //버튼리스너에 null값 주는 메소드
            binding.lowCardFront1.setOnClickListener(null);
            binding.lowCardFront2.setOnClickListener(null);
            binding.lowCardFront3.setOnClickListener(null);
            binding.lowCardFront4.setOnClickListener(null);
            binding.lowCardFront5.setOnClickListener(null);
            binding.lowCardFront6.setOnClickListener(null);
            binding.lowCardFront7.setOnClickListener(null);
            binding.lowCardFront8.setOnClickListener(null);
            binding.lowCardFront9.setOnClickListener(null);
            binding.lowCardFront10.setOnClickListener(null);
        }

        public void start() {            //시작했을때 카드를 바로 보여주는 메소드
            flipAnimation = new FlipAnimation(binding.lowCardFront1, binding.lowCardBack1);
            binding.lowMainCard1.startAnimation(flipAnimation);

            flipAnimation2 = new FlipAnimation(binding.lowCardFront2, binding.lowCardBack2);
            binding.lowMainCard2.startAnimation(flipAnimation2);

            flipAnimation3 = new FlipAnimation(binding.lowCardFront3, binding.lowCardBack3);
            binding.lowMainCard3.startAnimation(flipAnimation3);

            flipAnimation4 = new FlipAnimation(binding.lowCardFront4, binding.lowCardBack4);
            binding.lowMainCard4.startAnimation(flipAnimation4);

            flipAnimation5 = new FlipAnimation(binding.lowCardFront5, binding.lowCardBack5);
            binding.lowMainCard5.startAnimation(flipAnimation5);

            flipAnimation6 = new FlipAnimation(binding.lowCardFront6, binding.lowCardBack6);
            binding.lowMainCard6.startAnimation(flipAnimation6);

            flipAnimation7 = new FlipAnimation(binding.lowCardFront7, binding.lowCardBack7);
            binding.lowMainCard7.startAnimation(flipAnimation7);

            flipAnimation8 = new FlipAnimation(binding.lowCardFront8, binding.lowCardBack8);
            binding.lowMainCard8.startAnimation(flipAnimation8);

            flipAnimation9 = new FlipAnimation(binding.lowCardFront9, binding.lowCardBack9);
            binding.lowMainCard9.startAnimation(flipAnimation9);

            flipAnimation10 = new FlipAnimation(binding.lowCardFront10, binding.lowCardBack10);
            binding.lowMainCard10.startAnimation(flipAnimation10);
        }

        public void reverse() {        //start의 메소드가 실행된이후 3초후 카드를 뒤집는 메소드 실행
            flipAnimation.reverse();
            binding.lowMainCard1.startAnimation(flipAnimation);

            flipAnimation2.reverse();
            binding.lowMainCard2.startAnimation(flipAnimation2);

            flipAnimation3.reverse();
            binding.lowMainCard3.startAnimation(flipAnimation3);

            flipAnimation4.reverse();
            binding.lowMainCard4.startAnimation(flipAnimation4);

            flipAnimation5.reverse();
            binding.lowMainCard5.startAnimation(flipAnimation5);

            flipAnimation6.reverse();
            binding.lowMainCard6.startAnimation(flipAnimation6);

            flipAnimation7.reverse();
            binding.lowMainCard7.startAnimation(flipAnimation7);

            flipAnimation8.reverse();
            binding.lowMainCard8.startAnimation(flipAnimation8);

            flipAnimation9.reverse();
            binding.lowMainCard9.startAnimation(flipAnimation9);

            flipAnimation10.reverse();
            binding.lowMainCard10.startAnimation(flipAnimation10);
        }

        void flipCard(final int id) {  //id를 받아 어떤 아이디가 클릭됬는지를 확인해서 카드뒤집는 애니메이션을 실행하는 메소드
            if (id == 1) {
                flipAnimation = new FlipAnimation(binding.lowCardFront1, binding.lowCardBack1);
                binding.lowMainCard1.startAnimation(flipAnimation);
            } else if (id == 2) {
                flipAnimation2 = new FlipAnimation(binding.lowCardFront2, binding.lowCardBack2);
                binding.lowMainCard2.startAnimation(flipAnimation2);
            } else if (id == 3) {
                flipAnimation3 = new FlipAnimation(binding.lowCardFront3, binding.lowCardBack3);
                binding.lowMainCard3.startAnimation(flipAnimation3);
            } else if (id == 4) {
                flipAnimation4 = new FlipAnimation(binding.lowCardFront4, binding.lowCardBack4);
                binding.lowMainCard4.startAnimation(flipAnimation4);
            } else if (id == 5) {
                flipAnimation5 = new FlipAnimation(binding.lowCardFront5, binding.lowCardBack5);
                binding.lowMainCard5.startAnimation(flipAnimation5);
            } else if (id == 6) {
                flipAnimation6 = new FlipAnimation(binding.lowCardFront6, binding.lowCardBack6);
                binding.lowMainCard6.startAnimation(flipAnimation6);
            } else if (id == 7) {
                flipAnimation7 = new FlipAnimation(binding.lowCardFront7, binding.lowCardBack7);
                binding.lowMainCard7.startAnimation(flipAnimation7);
            } else if (id == 8) {
                flipAnimation8 = new FlipAnimation(binding.lowCardFront8, binding.lowCardBack8);
                binding.lowMainCard8.startAnimation(flipAnimation8);
            } else if (id == 9) {
                flipAnimation9 = new FlipAnimation(binding.lowCardFront9, binding.lowCardBack9);
                binding.lowMainCard9.startAnimation(flipAnimation9);
            } else if (id == 10) {
                flipAnimation10 = new FlipAnimation(binding.lowCardFront10, binding.lowCardBack10);
                binding.lowMainCard10.startAnimation(flipAnimation10);
            }

        }

        public void delay(long a) {          //빠르게 누르지 못하게 막는 타이머메소드
            timer2 = new CountDownTimer(a, 100) {
                @Override
                public void onTick(long millis) {
                }

                @Override
                public void onFinish() {
                    click(click);
                    clickcount = 0;
                    same = 0;
                    id = 0;
                    subid = 0;
                    Log.d("CardActivity", "timer delay");
                }
            };
            timer2.start();
        }
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        CustomViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
        }
    }

}
