package com.yang.card;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yang.bean.GCPage;
import com.yang.bean.GreetingCard;
import com.yang.parse.SocialMomentParse;
import com.yang.widget.KMTabWidget;
import com.yang.widget.MActionBar;
import com.yang.widget.SocailOrderMainView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joy on 7/6/2016.
 */
public class SocialCardEditActivity extends Activity implements View.OnClickListener{

    public final static int PAGE_FRONT = 0;
    public final static int PAGE_BACK = 1;

    private RelativeLayout socialCardEditContainer;
    private SocailOrderMainView socailOrderMainView;
    private TextView editButton,buyButton,social_order_price;
    private LinearLayout social_order_tools;
    private MActionBar social_card_title_bar;
    private GreetingCard greetingCard;
    private GCPage currentPage;
    private KMTabWidget vTitleTab;

    private float MainView_Minimum_Factor = 0.85f;
    private float MainView_Maximum_Factor = 1.0f;
    private int mainViewWidth = 0, mainViewHeight = 0;
    private RelativeLayout.LayoutParams mainViewParams;

    private List<Mode> records = new ArrayList<Mode>();
    private Mode lastMode = Mode.None;
    private String result="{\"Error\":null,\"GiftingProduct\":{\"ProductCreationServiceType\":\"GreetingCard\",\"GreetingCard\":{\"BaseURI\":\"https:\\/\\/kodakmomentsstage.kodakalaris.com\\/KodakGreetingCard\\/Service.svc\\/web\\/greetingcards\\/\",\"Id\":\"2cc8220953844876924a68e3fbeea325\",\"ProductDescriptionBaseURI\":\"https:\\/\\/kodakmomentsstage.kodakalaris.com\\/KodakRetailerCatalog\\/Service.svc\\/web\\/product-descriptions\\/\",\"ProductDescriptionId\":\"SocialMomentDuplex5x7S2H\",\"Theme\":\"SdPSMP000011ALLekc\",\"Pages\":[{\"BaseURI\":\"https:\\/\\/kodakmomentsstage.kodakalaris.com\\/KodakGreetingCard\\/Service.svc\\/web\\/greetingcards\\/pages\\/\",\"Id\":\"6365623742cf425aaca55774d2b82582\",\"SequenceNumber\":1,\"PageType\":\"CardFace\",\"LayoutType\":\"Fixed\",\"Width\":5.25,\"Height\":7.25,\"MinNumberOfImages\":2,\"MaxNumberOfImages\":2,\"Layers\":[{\"Type\":\"Image\",\"Location\":{\"X\":0.4975,\"Y\":0.4975,\"W\":4.255,\"H\":5.32,\"ContainerW\":5.25,\"ContainerH\":7.25},\"Angle\":0,\"Pinned\":true,\"ContentBaseURI\":\"https:\\/\\/kodakmomentsstage.kodakalaris.com\\/KodakImageEditing\\/Service.svc\\/web\\/images\\/\",\"ContentId\":\"576ea163c5e14df289d765dbd47976f9\",\"Data\":[{\"Name\":\"CropRegion\",\"Type\":6,\"ROIVal\":{\"X\":0,\"Y\":127.5952,\"W\":1054,\"H\":1317.8096,\"ContainerW\":1054,\"ContainerH\":1573}},{\"Name\":\"IsCaptionable\",\"Type\":0,\"BoolVal\":{\"Value\":false}}]},{\"Type\":\"TextBlock\",\"Location\":{\"X\":0.4975,\"Y\":5.915,\"W\":4.255,\"H\":0.67,\"ContainerW\":5.25,\"ContainerH\":7.25},\"Angle\":0,\"Pinned\":true,\"ContentBaseURI\":\"https:\\/\\/kodakmomentsstage.kodakalaris.com\\/KodakTextBlock\\/Service.svc\\/web\\/textblocks\\/\",\"ContentId\":\"12eb9997bcf043a996c9fdaf2bc94f38\",\"Data\":[{\"Name\":\"Visible\",\"Type\":0,\"BoolVal\":{\"Value\":true}},{\"Name\":\"SampleText\",\"Type\":7,\"StringVal\":{\"Value\":\"Your Text Here\"}},{\"Name\":\"Language\",\"Type\":7,\"StringVal\":{\"Value\":\"en_US\"}},{\"Name\":\"Alignment\",\"Type\":7,\"StringVal\":{\"Value\":\"Centered\"}},{\"Name\":\"Justification\",\"Type\":7,\"StringVal\":{\"Value\":\"Center\"}},{\"Name\":\"SupportsFormatTokens\",\"Type\":0,\"BoolVal\":{\"Value\":false}},{\"Name\":\"Font\",\"Type\":7,\"StringVal\":{\"Value\":\"Harriet Text Regular\"}},{\"Name\":\"FontPointSizeMin\",\"Type\":3,\"DoubleVal\":{\"Value\":5}},{\"Name\":\"FontPointSizeMax\",\"Type\":3,\"DoubleVal\":{\"Value\":9}},{\"Name\":\"Color\",\"Type\":7,\"StringVal\":{\"Value\":\"#FF3F3F3F\"}},{\"Name\":\"Opacity\",\"Type\":3,\"DoubleVal\":{\"Value\":1}},{\"Name\":\"Bold\",\"Type\":0,\"BoolVal\":{\"Value\":false}},{\"Name\":\"Italic\",\"Type\":0,\"BoolVal\":{\"Value\":false}},{\"Name\":\"StrikeThrough\",\"Type\":0,\"BoolVal\":{\"Value\":false}},{\"Name\":\"Underscore\",\"Type\":0,\"BoolVal\":{\"Value\":false}}],\"Label\":\"Moment.BlockQuote\"}],\"Margin\":{\"Top\":0,\"Left\":0,\"Bottom\":0,\"Right\":0}},{\"BaseURI\":\"https:\\/\\/kodakmomentsstage.kodakalaris.com\\/KodakGreetingCard\\/Service.svc\\/web\\/greetingcards\\/pages\\/\",\"Id\":\"6c16ec13eb41411a8023b442ae8e7783\",\"SequenceNumber\":2,\"PageType\":\"CardFace\",\"LayoutType\":\"Fixed\",\"Width\":5.25,\"Height\":7.25,\"MinNumberOfImages\":4,\"MaxNumberOfImages\":4,\"Layers\":[{\"Type\":\"TextBlock\",\"Location\":{\"X\":0.873466796875,\"Y\":0.50146720886231,\"W\":3.50306640625,\"H\":0.25773468017578,\"ContainerW\":5.25,\"ContainerH\":7.25},\"Angle\":0,\"Pinned\":true,\"ContentBaseURI\":\"https:\\/\\/kodakmomentsstage.kodakalaris.com\\/KodakTextBlock\\/Service.svc\\/web\\/textblocks\\/\",\"ContentId\":\"c23929b288214eabbbff7bc3db193f54\",\"Data\":[{\"Name\":\"Visible\",\"Type\":0,\"BoolVal\":{\"Value\":true}},{\"Name\":\"Text\",\"Type\":7,\"StringVal\":{\"Value\":\"Kay Alarismus\"}},{\"Name\":\"SampleText\",\"Type\":7,\"StringVal\":{\"Value\":\"Your Text Here\"}},{\"Name\":\"Language\",\"Type\":7,\"StringVal\":{\"Value\":\"en_US\"}},{\"Name\":\"Alignment\",\"Type\":7,\"StringVal\":{\"Value\":\"TopCenter\"}},{\"Name\":\"Justification\",\"Type\":7,\"StringVal\":{\"Value\":\"Center\"}},{\"Name\":\"SupportsFormatTokens\",\"Type\":0,\"BoolVal\":{\"Value\":false}},{\"Name\":\"Font\",\"Type\":7,\"StringVal\":{\"Value\":\"Harriet Display Bold\"}},{\"Name\":\"FontPointSizeMin\",\"Type\":3,\"DoubleVal\":{\"Value\":5}},{\"Name\":\"FontPointSizeMax\",\"Type\":3,\"DoubleVal\":{\"Value\":12}},{\"Name\":\"FontPointSizeMinMaxUsed\",\"Type\":3,\"DoubleVal\":{\"Value\":12}},{\"Name\":\"Color\",\"Type\":7,\"StringVal\":{\"Value\":\"#FF3F3F3F\"}},{\"Name\":\"Opacity\",\"Type\":3,\"DoubleVal\":{\"Value\":1}},{\"Name\":\"Bold\",\"Type\":0,\"BoolVal\":{\"Value\":true}},{\"Name\":\"Italic\",\"Type\":0,\"BoolVal\":{\"Value\":false}},{\"Name\":\"StrikeThrough\",\"Type\":0,\"BoolVal\":{\"Value\":false}},{\"Name\":\"Underscore\",\"Type\":0,\"BoolVal\":{\"Value\":false}}],\"Label\":\"Moment.Owner\"},{\"Type\":\"TextBlock\",\"Location\":{\"X\":2.16722564596023,\"Y\":1.0575,\"W\":0.91766288757324,\"H\":0.20000001907349,\"ContainerW\":5.25,\"ContainerH\":7.25},\"Angle\":0,\"Pinned\":true,\"ContentBaseURI\":\"https:\\/\\/kodakmomentsstage.kodakalaris.com\\/KodakTextBlock\\/Service.svc\\/web\\/textblocks\\/\",\"ContentId\":\"97f1b304bc114ddaacc25cb49c47aebb\",\"Data\":[{\"Name\":\"Visible\",\"Type\":0,\"BoolVal\":{\"Value\":true}},{\"Name\":\"Text\",\"Type\":7,\"StringVal\":{\"Value\":\"18 二月, 2016\"}},{\"Name\":\"SampleText\",\"Type\":7,\"StringVal\":{\"Value\":\"Your Text Here\"}},{\"Name\":\"Language\",\"Type\":7,\"StringVal\":{\"Value\":\"en_US\"}},{\"Name\":\"Alignment\",\"Type\":7,\"StringVal\":{\"Value\":\"Centered\"}},{\"Name\":\"Justification\",\"Type\":7,\"StringVal\":{\"Value\":\"Center\"}},{\"Name\":\"SupportsFormatTokens\",\"Type\":0,\"BoolVal\":{\"Value\":false}},{\"Name\":\"Font\",\"Type\":7,\"StringVal\":{\"Value\":\"Open Sans\"}},{\"Name\":\"FontPointSizeMin\",\"Type\":3,\"DoubleVal\":{\"Value\":5}},{\"Name\":\"FontPointSizeMax\",\"Type\":3,\"DoubleVal\":{\"Value\":6}},{\"Name\":\"FontPointSizeMinMaxUsed\",\"Type\":3,\"DoubleVal\":{\"Value\":6}},{\"Name\":\"Color\",\"Type\":7,\"StringVal\":{\"Value\":\"#FF3F3F3F\"}},{\"Name\":\"Opacity\",\"Type\":3,\"DoubleVal\":{\"Value\":1}},{\"Name\":\"Bold\",\"Type\":0,\"BoolVal\":{\"Value\":false}},{\"Name\":\"Italic\",\"Type\":0,\"BoolVal\":{\"Value\":false}},{\"Name\":\"StrikeThrough\",\"Type\":0,\"BoolVal\":{\"Value\":false}},{\"Name\":\"Underscore\",\"Type\":0,\"BoolVal\":{\"Value\":false}}],\"Label\":\"Moment.Time\"},{\"Type\":\"TextBlock\",\"Location\":{\"X\":0.932177734375,\"Y\":1.445,\"W\":3.38564453125,\"H\":4.57750030517578,\"ContainerW\":5.25,\"ContainerH\":7.25},\"Angle\":0,\"Pinned\":true,\"ContentBaseURI\":\"https:\\/\\/kodakmomentsstage.kodakalaris.com\\/KodakTextBlock\\/Service.svc\\/web\\/textblocks\\/\",\"ContentId\":\"5799e81011fd42f698ca49656c629dd9\",\"Data\":[{\"Name\":\"Visible\",\"Type\":0,\"BoolVal\":{\"Value\":true}},{\"Name\":\"Text\",\"Type\":7,\"StringVal\":{\"Value\":\"You Go Girl!\"}},{\"Name\":\"SampleText\",\"Type\":7,\"StringVal\":{\"Value\":\"Your Text Here\"}},{\"Name\":\"Language\",\"Type\":7,\"StringVal\":{\"Value\":\"en_US\"}},{\"Name\":\"Alignment\",\"Type\":7,\"StringVal\":{\"Value\":\"Centered\"}},{\"Name\":\"Justification\",\"Type\":7,\"StringVal\":{\"Value\":\"Center\"}},{\"Name\":\"SupportsFormatTokens\",\"Type\":0,\"BoolVal\":{\"Value\":true}},{\"Name\":\"Font\",\"Type\":7,\"StringVal\":{\"Value\":\"Harriet Text Regular\"}},{\"Name\":\"FontPointSizeMin\",\"Type\":3,\"DoubleVal\":{\"Value\":5}},{\"Name\":\"FontPointSizeMax\",\"Type\":3,\"DoubleVal\":{\"Value\":10}},{\"Name\":\"FontPointSizeMinMaxUsed\",\"Type\":3,\"DoubleVal\":{\"Value\":10}},{\"Name\":\"Color\",\"Type\":7,\"StringVal\":{\"Value\":\"#FF3F3F3F\"}},{\"Name\":\"Opacity\",\"Type\":3,\"DoubleVal\":{\"Value\":1}},{\"Name\":\"Bold\",\"Type\":0,\"BoolVal\":{\"Value\":false}},{\"Name\":\"Italic\",\"Type\":0,\"BoolVal\":{\"Value\":false}},{\"Name\":\"StrikeThrough\",\"Type\":0,\"BoolVal\":{\"Value\":false}},{\"Name\":\"Underscore\",\"Type\":0,\"BoolVal\":{\"Value\":false}}],\"Label\":\"Moment.Caption\"},{\"Type\":\"TextBlock\",\"Location\":{\"X\":1.67703524663323,\"Y\":6.2275,\"W\":1.89592971801758,\"H\":0.2,\"ContainerW\":5.25,\"ContainerH\":7.25},\"Angle\":0,\"Pinned\":true,\"ContentBaseURI\":\"https:\\/\\/kodakmomentsstage.kodakalaris.com\\/KodakTextBlock\\/Service.svc\\/web\\/textblocks\\/\",\"ContentId\":\"85bcdfd1142f4f5eb1c40c648903edcf\",\"Data\":[{\"Name\":\"Visible\",\"Type\":0,\"BoolVal\":{\"Value\":true}},{\"Name\":\"SampleText\",\"Type\":7,\"StringVal\":{\"Value\":\"Your Text Here\"}},{\"Name\":\"Language\",\"Type\":7,\"StringVal\":{\"Value\":\"en_US\"}},{\"Name\":\"Alignment\",\"Type\":7,\"StringVal\":{\"Value\":\"Centered\"}},{\"Name\":\"Justification\",\"Type\":7,\"StringVal\":{\"Value\":\"Center\"}},{\"Name\":\"SupportsFormatTokens\",\"Type\":0,\"BoolVal\":{\"Value\":false}},{\"Name\":\"Font\",\"Type\":7,\"StringVal\":{\"Value\":\"Open Sans\"}},{\"Name\":\"FontPointSizeMin\",\"Type\":3,\"DoubleVal\":{\"Value\":5}},{\"Name\":\"FontPointSizeMax\",\"Type\":3,\"DoubleVal\":{\"Value\":6}},{\"Name\":\"Color\",\"Type\":7,\"StringVal\":{\"Value\":\"#FF3F3F3F\"}},{\"Name\":\"Opacity\",\"Type\":3,\"DoubleVal\":{\"Value\":1}},{\"Name\":\"Bold\",\"Type\":0,\"BoolVal\":{\"Value\":false}},{\"Name\":\"Italic\",\"Type\":0,\"BoolVal\":{\"Value\":false}},{\"Name\":\"StrikeThrough\",\"Type\":0,\"BoolVal\":{\"Value\":false}},{\"Name\":\"Underscore\",\"Type\":0,\"BoolVal\":{\"Value\":false}}],\"Label\":\"Moment.Location\"}],\"Margin\":{\"Top\":0,\"Left\":0,\"Bottom\":0,\"Right\":0}}],\"IsDuplex\":false,\"MinNumberOfPages\":2,\"MaxNumberOfPages\":2,\"NumberOfPagesPerBaseCard\":2,\"MinNumberOfImages\":1,\"MaxNumberOfImages\":1,\"MaxNumberOfImagesPerAddedPage\":null,\"MaxNumberOfImagesPerBaseCard\":1,\"IdealNumberOfImagesPerBaseCard\":1,\"NumberOfImagesInCard\":1,\"NumberOfUnassignedImages\":null,\"SuggestedCaptionVisibility\":true,\"CanSetTitle\":false,\"CanSetSubtitle\":false,\"CanSetAuthor\":false}}}\n";
    private enum Mode {
        None, Edit
    }

    private boolean isZoomedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.social_order_edit_avtivity);
        records.add(Mode.None);
        initView();
        initData();
        setEvent();
    }

    public void initView(){
        socialCardEditContainer = (RelativeLayout)findViewById(R.id.social_card_edit_container);
        socailOrderMainView = (SocailOrderMainView) findViewById(R.id.social_order_mainview);
        social_card_title_bar = (MActionBar)findViewById(R.id.social_card_title_bar);
        editButton = (TextView) findViewById(R.id.social_order_tools_edit);
        buyButton = (TextView) findViewById(R.id.social_order_tools_buy);
        social_order_tools = (LinearLayout) findViewById(R.id.social_order_tools);
        social_order_price = (TextView) findViewById(R.id.social_order_price);
        vTitleTab = (KMTabWidget) findViewById(R.id.social_card_title_tab);

    }

    public void initData(){
        greetingCard = new SocialMomentParse().parseGreetingCard(result);
        socailOrderMainView.setGreetingCard(greetingCard);
        currentPage = socailOrderMainView.getPage();
        initPrice();
    }

    private void initPrice(){
        String price = "15$$$$$$$";
        social_order_price.setText(price);

    }



    public void setEvent(){
        editButton.setOnClickListener(this);
        buyButton.setOnClickListener(this);
        social_card_title_bar.setOnRightButtonClickListener(this);
        social_card_title_bar.setOnLeftButtonClickListener(this);

        socialCardEditContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return socailOrderMainView.dispatchTouchEvent(event);
            }
        });

        socailOrderMainView.setOnFlingListener(new SocailOrderMainView.onFlingListener() {
            @Override
            public void onFling(Boolean isClockWise) {
                if (socailOrderMainView.getSocialOrderPageViewfont().getVisibility() == View.GONE){
                    vTitleTab.setSelection(0);
                }else {
                    vTitleTab.setSelection(1);
                }
                socailOrderMainView.setGreetingCardCurrentPage();
                socailOrderMainView.CardOverTurnClockwise(isClockWise);
            }

            @Override
            public void onClick() {
            }
        });

        vTitleTab.setOnItemSelectedListener(new KMTabWidget.OnItemSelectedListener() {
            @Override
            public void onItemSelected(KMTabWidget parent, int position) {
                socailOrderMainView.setGreetingCardCurrentPage();
                switch (position){
                    case PAGE_FRONT:
                        currentPage = greetingCard.pages[0];
                        socailOrderMainView.CardOverTurnClockwise(true);
                        break;
                    case PAGE_BACK:
                        currentPage = greetingCard.pages[1];
                        socailOrderMainView.CardOverTurnClockwise(false);
                        break;
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == editButton.getId()){
            forwarToMode(Mode.Edit);
            social_card_title_bar.setRightButtonVisiable(true);
            social_card_title_bar.setRightText(R.string.zoomIn);
        }
        else if (viewId == R.id.tv_right){
            social_card_title_bar.setRightButtonVisiable(false);
            backToPreviousMode();
        }
        else if (viewId == R.id.tv_left || v.getId() == R.id.ibtn_left){
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        backToPreviousMode();
    }

    private void forwarToMode(Mode toMode){
        lastMode = records.get(records.size() - 1);
        records.add(toMode);
        goToMode(toMode);
    }

    private void backToPreviousMode(){
        if(records.size() == 1){
            finish();
        } else {
            lastMode = records.get(records.size() - 1);
            records.remove(records.size() - 1);
            Mode mode = records.get(records.size() - 1);
            goToMode(mode);
        }
    }

    private void goToMode(Mode toMode){
        switch (toMode){
            case None:
                zoomOutCardPages();
                vTitleTab.setVisibility(View.VISIBLE);
                social_order_tools.setVisibility(View.VISIBLE);
                social_order_price.setVisibility(View.VISIBLE);
                social_card_title_bar.setRightButtonVisiable(false);
                break;
            case Edit:
                if(!isZoomedIn) {
                    zoomInCardPages();
                }
                social_order_tools.setVisibility(View.GONE);
                social_order_price.setVisibility(View.INVISIBLE);
                social_card_title_bar.setRightButtonVisiable(true);
                social_card_title_bar.setRightText(R.string.zoomIn);
                break;
        }
    }

    private int getPageId() {
        return vTitleTab.getSelectedItemPosition();
    }

    private void zoomOutCardPages(){
        isZoomedIn = false;
        int currentWidth = socailOrderMainView.getWidth();
        int currentHeight = socailOrderMainView.getHeight();
        int finalWidth = (int) (mainViewWidth * MainView_Minimum_Factor);
        int finalHeight = (int) (mainViewHeight * MainView_Minimum_Factor);
        startZoomAnimation(currentWidth, finalWidth, currentHeight, finalHeight);
    }

    private void zoomInCardPages(){
        isZoomedIn = true;
        int currentWidth = socailOrderMainView.getWidth();
        int currentHeight = socailOrderMainView.getHeight();
        int finalWidth = (int) (mainViewWidth * MainView_Maximum_Factor);
        int finalHeight = (int) (mainViewHeight * MainView_Maximum_Factor);
        startZoomAnimation(currentWidth, finalWidth, currentHeight, finalHeight);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            if(mainViewWidth == 0 && mainViewHeight == 0){
                mainViewWidth = socailOrderMainView.getWidth();
                mainViewHeight = socailOrderMainView.getHeight();
                mainViewParams = (RelativeLayout.LayoutParams) socailOrderMainView.getLayoutParams();
                mainViewParams.height = (int) (mainViewHeight * MainView_Minimum_Factor);
                mainViewParams.width = (int) (mainViewWidth * MainView_Minimum_Factor);
                socailOrderMainView.setLayoutParams(mainViewParams);
            }
        }
    }

    private void startZoomAnimation(int startWidth, int endWidth, int startHeight, int endHeight){
        ValueAnimator animatorWidth = ObjectAnimator.ofInt(startWidth, endWidth);
        animatorWidth.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int val = (int) animation.getAnimatedValue();
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) socailOrderMainView.getLayoutParams();
                layoutParams.width = val;
                socailOrderMainView.setLayoutParams(layoutParams);
            }
        });
        ValueAnimator animatorHeight = ObjectAnimator.ofInt(startHeight, endHeight);
        animatorHeight.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int val = (int) animation.getAnimatedValue();
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) socailOrderMainView.getLayoutParams();
                layoutParams.height = val;
                socailOrderMainView.setLayoutParams(layoutParams);
            }
        });

        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(350);
        animSet.play(animatorWidth).with(animatorHeight);
        animSet.start();
    }
}
