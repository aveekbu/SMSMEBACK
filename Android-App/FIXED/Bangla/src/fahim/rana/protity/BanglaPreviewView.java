package fahim.rana.protity;

import java.util.ArrayList;
import java.util.List;







import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ImageSpan;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

public class BanglaPreviewView extends TextView {

	String b_alpha[]= {"অ","আ","ই","ঈ","উ","ঊ","ঋ","এ","ঐ","ও","ঔ","ক","খ","গ","ঘ","ঙ","চ","ছ","জ","ঝ","ঞ","ট","ঠ","ড","ঢ","ণ","ত","থ","দ","ধ","ন","প","ফ","ব","ভ","ম","য","র","ল","শ","ষ","স","হ","ড়","ঢ়","য়","ৎ","ং","ঃ","া","ে","ৈ","▫","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
String jukto[] =  
{"ক্ট","ক্স","শ্ব","শ্ন","শ্ত","ষ্ক","ষ্প","ষ্ঠ","ষ্ট","স্খ","স্ট","ত্ম","ত্ন","ত্ত্ব","ট্ট","স্ত্ব","ন্দ্ব","ন্ড","ন্স","ণ্ট","ণ্ঠ","ণ্ন","ল্ড","ল্গ","প্ট","প্প","প্স","স্ফ","স্ম","স্ন","স্ক","স্প","স্ত","দ্ব","দ্ধ","ফ্ল","জ্ঞ","গ্ধ","গ্ব","হ্ন","হ্ণ","জ্জ্ব","জ্জ","ঙ্ক্ষ","ক্ষ্ম","ক্ত","ল্ক","ল্প","ধ্ব","ড্ড","ঙ্গ","ক্ষ","চ্চ","ভ্ল","ব্ব","ব্দ","ন্ন","ন্থ","ন্ত্ব","ম্ম","ম্প","ম্ভ","ম্ব","ক্ল","শ্চ","শ্ছ","শ্ম","ষ্ণ","ষ্ম","ত্ত","ত্থ","থ্ব","ন্ত","ণ্ব","ন্ব","ঞ্চ","ঞ্ছ","ঞ্জ","ল্ম","ল্ব","প্ন","প্ট","স্ল","ম্ল","স্থ","স্ব","দ্দ","দ্ম","ঘ্ন","গ্ম","গ্ন","হ্ব","হ্ম","জ্ব","ক্ক","ক্ম","ক্ব","ল্ল","ল্ট","দ্ভ","ঙ্ক","ঙ্ঘ","চ্ছ","ব্ধ","ব্জ","ন্দ","ন্ধ","ন্ট","ম্ন","ম্ফ","ত্ব"};
	
	String juktor[] =  {"➀","➁","➂","➃","➄","➅","➆","➇","➈","➉","➊","➋","➌","➍","➎","➏","➐","➑","➒","➓","Ⓐ","Ⓑ","Ⓒ","Ⓓ","Ⓔ","Ⓕ","Ⓖ","Ⓗ","Ⓘ","Ⓙ","Ⓚ","Ⓛ","Ⓜ","Ⓝ","Ⓞ","Ⓟ","Ⓠ","Ⓡ","Ⓢ","Ⓣ","Ⓤ","Ⓥ","Ⓦ","Ⓧ","Ⓨ","Ⓩ","ⓐ","ⓑ","ⓒ","ⓓ","ⓔ","ⓕ","ⓖ","ⓗ","ⓘ","ⓙ","ⓚ","ⓛ","ⓜ","ⓝ","ⓞ","ⓟ","ⓠ","ⓡ","ⓢ","ⓣ","ⓤ","ⓥ","ⓦ","ⓧ","ⓨ","ⓩ","┌","┍","┎","┏","┐","┑","┒","┓","└","┕","┖","┗","┘","┙","┚","┛","├","┝","┞","┟","┠","┡","┢","┣","┤","┥","┦","┧","┨","┩","┪","┫","┬","┭","┮","┯","┰","┱","┲"};
	int mark =0;
	String kar =  "ি"+"ী"+"ু"+"ূ"+"ৃ"+"▴"+"▶"+"ৗ"+"্‌";
	

	public static ArrayList<String> sptype = new ArrayList<String>();	
	ArrayList<Integer> arrays = new ArrayList<Integer>();
	ArrayList<Integer> arrayo = new ArrayList<Integer>();
	ArrayList<Bitmap> bitmapArrayJ = new ArrayList<Bitmap>();
	ArrayList<Bitmap> bitmapArray = new ArrayList<Bitmap>();
	ArrayList<Bitmap> bitmapKar = new ArrayList<Bitmap>();
	
	
	
	Bitmap ko = BitmapFactory.decodeResource( getResources(), R.drawable.ko);
	Bitmap i_kar = BitmapFactory.decodeResource( getResources(), R.drawable.i_kar1);
	Bitmap hrosu = BitmapFactory.decodeResource( getResources(), R.drawable.u_kar);
	Bitmap dirghu = BitmapFactory.decodeResource( getResources(), R.drawable.uu_kar);
	Bitmap hri = BitmapFactory.decodeResource( getResources(), R.drawable.rri_kar);
	Bitmap oi = BitmapFactory.decodeResource( getResources(), R.drawable.oi_kar);
	Bitmap dirghi = BitmapFactory.decodeResource( getResources(), R.drawable.ii_kar1);
	Bitmap hsnto = BitmapFactory.decodeResource( getResources(), R.drawable.hoshonto);
	Bitmap jofla = BitmapFactory.decodeResource( getResources(), R.drawable.z_fola);
	Bitmap rofla = BitmapFactory.decodeResource( getResources(), R.drawable.ro_fola);
	Bitmap ref = BitmapFactory.decodeResource( getResources(), R.drawable.r_fola); 
	Bitmap space = BitmapFactory.decodeResource( getResources(), R.drawable.space);
	Bitmap ou1 = BitmapFactory.decodeResource( getResources(), R.drawable.ou_kar1);
	
	Bitmap coma = BitmapFactory.decodeResource( getResources(), R.drawable.coma);	
	Bitmap dari = BitmapFactory.decodeResource( getResources(), R.drawable.dari);
	Bitmap que = BitmapFactory.decodeResource( getResources(), R.drawable.que);
	// bangla num
	
	Bitmap z11 = BitmapFactory.decodeResource( getResources(), R.drawable.z11);
	Bitmap z22 = BitmapFactory.decodeResource( getResources(), R.drawable.z22);
	Bitmap z33 = BitmapFactory.decodeResource( getResources(), R.drawable.z33);
	Bitmap z44 = BitmapFactory.decodeResource( getResources(), R.drawable.z44);
	Bitmap z55 = BitmapFactory.decodeResource( getResources(), R.drawable.z55);
	Bitmap z66 = BitmapFactory.decodeResource( getResources(), R.drawable.z66);
	Bitmap z77 = BitmapFactory.decodeResource( getResources(), R.drawable.z77);
	Bitmap z88 = BitmapFactory.decodeResource( getResources(), R.drawable.z88);
	Bitmap z99 = BitmapFactory.decodeResource( getResources(), R.drawable.z99);
	Bitmap z00 = BitmapFactory.decodeResource( getResources(), R.drawable.z00);
	
	
	
	Bitmap z1 = BitmapFactory.decodeResource( getResources(), R.drawable.z1);
	Bitmap z2 = BitmapFactory.decodeResource( getResources(), R.drawable.z2);
	Bitmap z3 = BitmapFactory.decodeResource( getResources(), R.drawable.z3);
	Bitmap z4 = BitmapFactory.decodeResource( getResources(), R.drawable.z4);
	Bitmap z5 = BitmapFactory.decodeResource( getResources(), R.drawable.z5);
	Bitmap z6 = BitmapFactory.decodeResource( getResources(), R.drawable.z6);
	Bitmap z7 = BitmapFactory.decodeResource( getResources(), R.drawable.z7);
	Bitmap z8 = BitmapFactory.decodeResource( getResources(), R.drawable.z8);
	Bitmap z9 = BitmapFactory.decodeResource( getResources(), R.drawable.z9);
	Bitmap z0 = BitmapFactory.decodeResource( getResources(), R.drawable.z0);
	
	
	
	//
	
	
	
	public BanglaPreviewView(Context context) {
        super(context);
      //  sptype.add(" ");
        
        sptype.add(" ");
       
        sptype.add("1");
        sptype.add("2");
        sptype.add("3");
        sptype.add("4");
        sptype.add("5");
        sptype.add("6");
        sptype.add("7");
        sptype.add("8");
        sptype.add("9");
        sptype.add("0");
        sptype.add("০");
        sptype.add("১");
        sptype.add("২");
        sptype.add("৩");
        sptype.add("৪");
        sptype.add("৫");
        sptype.add("৬");
        sptype.add("৭");
        sptype.add("৮");
        sptype.add("৯");
        sptype.add("০");
        sptype.add("।");
        sptype.add("!");
        sptype.add("@");
        sptype.add("\"");
        sptype.add("\'");
 
        sptype.add(",");
        sptype.add("*");
        sptype.add(":");
        sptype.add("?");
        sptype.add(";");


        
        
        
      // vkar replace  
        bitmapKar.add(i_kar);
        bitmapKar.add(dirghi);
        bitmapKar.add(hrosu);
        bitmapKar.add(dirghu);
        bitmapKar.add(hri);
        bitmapKar.add(ref);
        bitmapKar.add(rofla);
        bitmapKar.add(ou1);
        bitmapKar.add(hsnto);
        bitmapKar.add(space);
        bitmapKar.add(space);
    //
     
        
       
        
        
        
        // alphabet
        
        
        Bitmap b1 = BitmapFactory.decodeResource( getResources(), R.drawable.shore_o);
    	Bitmap b2 = BitmapFactory.decodeResource( getResources(), R.drawable.shore_a);
    	Bitmap b3 = BitmapFactory.decodeResource( getResources(), R.drawable.hrosho_e);
    	Bitmap b4 = BitmapFactory.decodeResource( getResources(), R.drawable.dirgho_e);
    	Bitmap b5 = BitmapFactory.decodeResource( getResources(), R.drawable.u);
    	Bitmap b6 = BitmapFactory.decodeResource( getResources(), R.drawable.uu);
    	Bitmap b7 = BitmapFactory.decodeResource( getResources(), R.drawable.ri);
    	Bitmap b8 = BitmapFactory.decodeResource( getResources(), R.drawable.e);
    	Bitmap b9 = BitmapFactory.decodeResource( getResources(), R.drawable.oi);
    	Bitmap b10 = BitmapFactory.decodeResource( getResources(), R.drawable.o);
    	Bitmap b11 = BitmapFactory.decodeResource( getResources(), R.drawable.ou);
    	Bitmap b12 = BitmapFactory.decodeResource( getResources(), R.drawable.ko);
    	Bitmap b13 = BitmapFactory.decodeResource( getResources(), R.drawable.kho);
    	Bitmap b14 = BitmapFactory.decodeResource( getResources(), R.drawable.go);
        Bitmap b15 = BitmapFactory.decodeResource( getResources(), R.drawable.gho);
    	Bitmap b16 = BitmapFactory.decodeResource( getResources(), R.drawable.umo);
    	Bitmap b17 = BitmapFactory.decodeResource( getResources(), R.drawable.co);
    	Bitmap b18 = BitmapFactory.decodeResource( getResources(), R.drawable.cho);
    	Bitmap b19 = BitmapFactory.decodeResource( getResources(), R.drawable.jo);
    	Bitmap b20 = BitmapFactory.decodeResource( getResources(), R.drawable.jho);
    	Bitmap b21 = BitmapFactory.decodeResource( getResources(), R.drawable.nio);
    	Bitmap b22 = BitmapFactory.decodeResource( getResources(), R.drawable.t);
    	Bitmap b23 = BitmapFactory.decodeResource( getResources(), R.drawable.th);
    	Bitmap b24 = BitmapFactory.decodeResource( getResources(), R.drawable.do1);
    	Bitmap b25 = BitmapFactory.decodeResource( getResources(), R.drawable.dhoo);
    	Bitmap b26 = BitmapFactory.decodeResource( getResources(), R.drawable.n122);
    	Bitmap b27 = BitmapFactory.decodeResource( getResources(), R.drawable.to);
    	Bitmap b28 = BitmapFactory.decodeResource( getResources(), R.drawable.tho);
    	Bitmap b29 = BitmapFactory.decodeResource( getResources(), R.drawable.d);
    	Bitmap b30 = BitmapFactory.decodeResource( getResources(), R.drawable.dho);
    	Bitmap b31 = BitmapFactory.decodeResource( getResources(), R.drawable.no);
    	Bitmap b32 = BitmapFactory.decodeResource( getResources(), R.drawable.po);
    	Bitmap b33 = BitmapFactory.decodeResource( getResources(), R.drawable.fo);
    	Bitmap b34 = BitmapFactory.decodeResource( getResources(), R.drawable.bo);
    	Bitmap b35 = BitmapFactory.decodeResource( getResources(), R.drawable.vo);
    	Bitmap b36 = BitmapFactory.decodeResource( getResources(), R.drawable.mo);
    	Bitmap b37 = BitmapFactory.decodeResource( getResources(), R.drawable.zo);
    	Bitmap b38 = BitmapFactory.decodeResource( getResources(), R.drawable.ro);
    	Bitmap b39 = BitmapFactory.decodeResource( getResources(), R.drawable.lo);
    	Bitmap b40 = BitmapFactory.decodeResource( getResources(), R.drawable.sho);
    	Bitmap b41 = BitmapFactory.decodeResource( getResources(), R.drawable.shoo);
    	Bitmap b42 = BitmapFactory.decodeResource( getResources(), R.drawable.so);
    	Bitmap b43 = BitmapFactory.decodeResource( getResources(), R.drawable.ho);
    	Bitmap b44 = BitmapFactory.decodeResource( getResources(), R.drawable.roo);
    	Bitmap b45 = BitmapFactory.decodeResource( getResources(), R.drawable.ru);
    	Bitmap b46 = BitmapFactory.decodeResource( getResources(), R.drawable.onto_io);
    	Bitmap b47 = BitmapFactory.decodeResource( getResources(), R.drawable.kho_to);
    	Bitmap b48 = BitmapFactory.decodeResource( getResources(), R.drawable.onushar);
    	Bitmap b49 = BitmapFactory.decodeResource( getResources(), R.drawable.bishorgo);
    	Bitmap b50 = BitmapFactory.decodeResource( getResources(), R.drawable.a_kar);
    	Bitmap b51 = BitmapFactory.decodeResource( getResources(), R.drawable.e_kar);
    	Bitmap b52 = BitmapFactory.decodeResource( getResources(), R.drawable.oi_kar);
    	
    	Bitmap b54 = BitmapFactory.decodeResource( getResources(), R.drawable.z_fola);
        
        

    	Bitmap a = BitmapFactory.decodeResource( getResources(), R.drawable.aaaaa);
    	Bitmap b = BitmapFactory.decodeResource( getResources(), R.drawable.bbbbb);
    	Bitmap c = BitmapFactory.decodeResource( getResources(), R.drawable.ccccc);
    	Bitmap d = BitmapFactory.decodeResource( getResources(), R.drawable.ddddd);
    	Bitmap e = BitmapFactory.decodeResource( getResources(), R.drawable.eeeee);
    	Bitmap f = BitmapFactory.decodeResource( getResources(), R.drawable.fffff);
    	Bitmap g = BitmapFactory.decodeResource( getResources(), R.drawable.ggggg);
    	Bitmap h = BitmapFactory.decodeResource( getResources(), R.drawable.hhhhh);
    	Bitmap i = BitmapFactory.decodeResource( getResources(), R.drawable.iiiii);
    	Bitmap j = BitmapFactory.decodeResource( getResources(), R.drawable.jjjjj);
    	Bitmap k = BitmapFactory.decodeResource( getResources(), R.drawable.kkkkk);
    	Bitmap l = BitmapFactory.decodeResource( getResources(), R.drawable.lllll);
    	Bitmap m = BitmapFactory.decodeResource( getResources(), R.drawable.mmmmm);
    	Bitmap n = BitmapFactory.decodeResource( getResources(), R.drawable.nnnnn);
    	Bitmap o = BitmapFactory.decodeResource( getResources(), R.drawable.ooooo);
    	Bitmap p = BitmapFactory.decodeResource( getResources(), R.drawable.ppppp);
    	Bitmap q = BitmapFactory.decodeResource( getResources(), R.drawable.qqqqq);
    	Bitmap r = BitmapFactory.decodeResource( getResources(), R.drawable.rrrrr);
    	Bitmap  s= BitmapFactory.decodeResource( getResources(), R.drawable.sssss);
    	Bitmap  t= BitmapFactory.decodeResource( getResources(), R.drawable.ttttt);
    	Bitmap  u= BitmapFactory.decodeResource( getResources(), R.drawable.uuuuu);
    	Bitmap  v= BitmapFactory.decodeResource( getResources(), R.drawable.vvvvv);
    	Bitmap  w= BitmapFactory.decodeResource( getResources(), R.drawable.wwwww);
    	Bitmap  x= BitmapFactory.decodeResource( getResources(), R.drawable.xxxxx);
    	Bitmap  y= BitmapFactory.decodeResource( getResources(), R.drawable.yyyyy);
    	Bitmap  z= BitmapFactory.decodeResource( getResources(), R.drawable.zzzzz);
    	Bitmap  A= BitmapFactory.decodeResource( getResources(), R.drawable.aaaa);
    	Bitmap  B= BitmapFactory.decodeResource( getResources(), R.drawable.bbbb);
    	Bitmap  C= BitmapFactory.decodeResource( getResources(), R.drawable.cccc);
    	Bitmap  D= BitmapFactory.decodeResource( getResources(), R.drawable.dddd);
    	Bitmap  E= BitmapFactory.decodeResource( getResources(), R.drawable.eeee);
    	Bitmap  F= BitmapFactory.decodeResource( getResources(), R.drawable.ffff);
    	Bitmap  G= BitmapFactory.decodeResource( getResources(), R.drawable.gggg);
    	Bitmap  H= BitmapFactory.decodeResource( getResources(), R.drawable.hhhh);
    	Bitmap  I= BitmapFactory.decodeResource( getResources(), R.drawable.iiii);
    	Bitmap  J= BitmapFactory.decodeResource( getResources(), R.drawable.jjjj);
    	Bitmap  K= BitmapFactory.decodeResource( getResources(), R.drawable.kkkk);
    	Bitmap  L= BitmapFactory.decodeResource( getResources(), R.drawable.llll);
    	Bitmap  M= BitmapFactory.decodeResource( getResources(), R.drawable.mmmm);
    	Bitmap  N= BitmapFactory.decodeResource( getResources(), R.drawable.nnnn);
    	Bitmap  O= BitmapFactory.decodeResource( getResources(), R.drawable.oooo);
    	Bitmap  P= BitmapFactory.decodeResource( getResources(), R.drawable.pppp);
    	Bitmap  Q= BitmapFactory.decodeResource( getResources(), R.drawable.qqqq);
    	Bitmap  RR= BitmapFactory.decodeResource( getResources(), R.drawable.rrrr);
    	Bitmap  S= BitmapFactory.decodeResource( getResources(), R.drawable.ssss);
    	Bitmap  T= BitmapFactory.decodeResource( getResources(), R.drawable.tttt);
    	Bitmap  U= BitmapFactory.decodeResource( getResources(), R.drawable.uuuu);
    	Bitmap  V= BitmapFactory.decodeResource( getResources(), R.drawable.vvvv);
    	Bitmap  W= BitmapFactory.decodeResource( getResources(), R.drawable.wwww);
    	Bitmap  X= BitmapFactory.decodeResource( getResources(), R.drawable.xxxx);
    	Bitmap  Y= BitmapFactory.decodeResource( getResources(), R.drawable.yyyy);
    	Bitmap  Z= BitmapFactory.decodeResource( getResources(), R.drawable.zzzz);
        
        //
    	
    	
    	//
    	Bitmap j1 = BitmapFactory.decodeResource(getResources(),R.drawable.kto1);
    	Bitmap j2 = BitmapFactory.decodeResource(getResources(),R.drawable.kso);
    	Bitmap j3 = BitmapFactory.decodeResource(getResources(),R.drawable.shwo);
    	Bitmap j4 = BitmapFactory.decodeResource(getResources(),R.drawable.shno);
    	Bitmap j5 = BitmapFactory.decodeResource(getResources(),R.drawable.shto12);
    	Bitmap j6 = BitmapFactory.decodeResource(getResources(),R.drawable.shko1);
    	Bitmap j7 = BitmapFactory.decodeResource(getResources(),R.drawable.shpo1);
    	Bitmap j8 = BitmapFactory.decodeResource(getResources(),R.drawable.shtho1);
    	Bitmap j9 = BitmapFactory.decodeResource(getResources(),R.drawable.shto1);
    	Bitmap j10 = BitmapFactory.decodeResource(getResources(),R.drawable.skho);
    	Bitmap j11 = BitmapFactory.decodeResource(getResources(),R.drawable.sto1);
    	
    	Bitmap j12 = BitmapFactory.decodeResource(getResources(),R.drawable.tmo);
    	Bitmap j13 = BitmapFactory.decodeResource(getResources(),R.drawable.tno);
    	Bitmap j14 = BitmapFactory.decodeResource(getResources(),R.drawable.ttwo);
    	Bitmap j15 = BitmapFactory.decodeResource(getResources(),R.drawable.tto);
    	Bitmap j16 = BitmapFactory.decodeResource(getResources(),R.drawable.stwo);
    	Bitmap j17 = BitmapFactory.decodeResource(getResources(),R.drawable.ndwo);
    	Bitmap j18 = BitmapFactory.decodeResource(getResources(),R.drawable.ndo1);//
    	Bitmap j19 = BitmapFactory.decodeResource(getResources(),R.drawable.ns);
    	Bitmap j20 = BitmapFactory.decodeResource(getResources(),R.drawable.nto1);
    	Bitmap j21 = BitmapFactory.decodeResource(getResources(),R.drawable.ntho1);
    	Bitmap j22 = BitmapFactory.decodeResource(getResources(),R.drawable.nnno);
    	
    	Bitmap j23 = BitmapFactory.decodeResource(getResources(),R.drawable.ld1);
    	Bitmap j24 = BitmapFactory.decodeResource(getResources(),R.drawable.lgo);
    	Bitmap j25 = BitmapFactory.decodeResource(getResources(),R.drawable.pto1);
    	Bitmap j26 = BitmapFactory.decodeResource(getResources(),R.drawable.pp);
    	Bitmap j27 = BitmapFactory.decodeResource(getResources(),R.drawable.pso);
    	Bitmap j28 = BitmapFactory.decodeResource(getResources(),R.drawable.sfo);
    	Bitmap j29 = BitmapFactory.decodeResource(getResources(),R.drawable.smo);
    	Bitmap j30 = BitmapFactory.decodeResource(getResources(),R.drawable.sno);
    	Bitmap j31 = BitmapFactory.decodeResource(getResources(),R.drawable.sko);
    	Bitmap j32 = BitmapFactory.decodeResource(getResources(),R.drawable.spo);
    	Bitmap j33 = BitmapFactory.decodeResource(getResources(),R.drawable.stoo);
    	
    	Bitmap j34 = BitmapFactory.decodeResource(getResources(),R.drawable.dbo);
    	Bitmap j35 = BitmapFactory.decodeResource(getResources(),R.drawable.ddho);
    	Bitmap j36 = BitmapFactory.decodeResource(getResources(),R.drawable.flo);
    	Bitmap j37 = BitmapFactory.decodeResource(getResources(),R.drawable.ggo);
    	Bitmap j38 = BitmapFactory.decodeResource(getResources(),R.drawable.gdho);
    	Bitmap j39 = BitmapFactory.decodeResource(getResources(),R.drawable.gwo);
    	Bitmap j40 = BitmapFactory.decodeResource(getResources(),R.drawable.hno);
    	Bitmap j41 = BitmapFactory.decodeResource(getResources(),R.drawable.hn1);
    	Bitmap j42 = BitmapFactory.decodeResource(getResources(),R.drawable.jjwo);
    	Bitmap j43 = BitmapFactory.decodeResource(getResources(),R.drawable.jjo);
    	Bitmap j44 = BitmapFactory.decodeResource(getResources(),R.drawable.ngkkho);
    	Bitmap j45 = BitmapFactory.decodeResource(getResources(),R.drawable.kkhmo);
    	
    	Bitmap j46 = BitmapFactory.decodeResource(getResources(),R.drawable.kto);
    	Bitmap j47 = BitmapFactory.decodeResource(getResources(),R.drawable.lko);
    	Bitmap j48 = BitmapFactory.decodeResource(getResources(),R.drawable.lpo);
    	Bitmap j49 = BitmapFactory.decodeResource(getResources(),R.drawable.dhwo);
    	Bitmap j50 = BitmapFactory.decodeResource(getResources(),R.drawable.dd1);
    	Bitmap j51 = BitmapFactory.decodeResource(getResources(),R.drawable.ngo);
    	Bitmap j52 = BitmapFactory.decodeResource(getResources(),R.drawable.kkho);
    	Bitmap j53 = BitmapFactory.decodeResource(getResources(),R.drawable.cco);
    	Bitmap j54 = BitmapFactory.decodeResource(getResources(),R.drawable.vlo);
    	Bitmap j55 = BitmapFactory.decodeResource(getResources(),R.drawable.bbo);
    	Bitmap j56 = BitmapFactory.decodeResource(getResources(),R.drawable.bdo);
    	
    	Bitmap j57 = BitmapFactory.decodeResource(getResources(),R.drawable.nno);
    	Bitmap j58 = BitmapFactory.decodeResource(getResources(),R.drawable.ntho);
    	Bitmap j59 = BitmapFactory.decodeResource(getResources(),R.drawable.ntwo);
    	Bitmap j60 = BitmapFactory.decodeResource(getResources(),R.drawable.mmo);
    	Bitmap j61 = BitmapFactory.decodeResource(getResources(),R.drawable.mpo);
    	Bitmap j62 = BitmapFactory.decodeResource(getResources(),R.drawable.mvo);
    	Bitmap j63 = BitmapFactory.decodeResource(getResources(),R.drawable.mbo);
    	Bitmap j64 = BitmapFactory.decodeResource(getResources(),R.drawable.klo);
    	
    	Bitmap j66 = BitmapFactory.decodeResource(getResources(),R.drawable.shco);
    	Bitmap j67 = BitmapFactory.decodeResource(getResources(),R.drawable.shcho);
    	Bitmap j68 = BitmapFactory.decodeResource(getResources(),R.drawable.shmo);
    	
    	Bitmap j69 = BitmapFactory.decodeResource(getResources(),R.drawable.shno1);
    	Bitmap j70 = BitmapFactory.decodeResource(getResources(),R.drawable.shmo1);
    	
    	
    	Bitmap j73 = BitmapFactory.decodeResource(getResources(),R.drawable.tto1112);
    	
    	Bitmap j75 = BitmapFactory.decodeResource(getResources(),R.drawable.ttho);
    	Bitmap j76 = BitmapFactory.decodeResource(getResources(),R.drawable.tthwo);
    	Bitmap j77 = BitmapFactory.decodeResource(getResources(),R.drawable.ntoo);
    	Bitmap j78 = BitmapFactory.decodeResource(getResources(),R.drawable.nwo);
    	Bitmap j79 = BitmapFactory.decodeResource(getResources(),R.drawable.nwo12);
    	Bitmap j80 = BitmapFactory.decodeResource(getResources(),R.drawable.nco1);
    	
    	Bitmap j81 = BitmapFactory.decodeResource(getResources(),R.drawable.ncho1);
    	Bitmap j82 = BitmapFactory.decodeResource(getResources(),R.drawable.njo1);
    	Bitmap j83 = BitmapFactory.decodeResource(getResources(),R.drawable.lmo);
    	Bitmap j84 = BitmapFactory.decodeResource(getResources(),R.drawable.lw);
    	Bitmap j85 = BitmapFactory.decodeResource(getResources(),R.drawable.pno);
    	Bitmap j86 = BitmapFactory.decodeResource(getResources(),R.drawable.pto1);
    	Bitmap j87 = BitmapFactory.decodeResource(getResources(),R.drawable.slo);
    	Bitmap j88 = BitmapFactory.decodeResource(getResources(),R.drawable.mlo);
    	Bitmap j89 = BitmapFactory.decodeResource(getResources(),R.drawable.stho);
    	Bitmap j90 = BitmapFactory.decodeResource(getResources(),R.drawable.swo);
    	Bitmap j91 = BitmapFactory.decodeResource(getResources(),R.drawable.ddo);
    	Bitmap j92 = BitmapFactory.decodeResource(getResources(),R.drawable.dmo);
    	Bitmap j93 = BitmapFactory.decodeResource(getResources(),R.drawable.ghno);
    	Bitmap j94 = BitmapFactory.decodeResource(getResources(),R.drawable.gmo);
    	Bitmap j95 = BitmapFactory.decodeResource(getResources(),R.drawable.gno);
    	Bitmap j96 = BitmapFactory.decodeResource(getResources(),R.drawable.hbo);
    	Bitmap j97 = BitmapFactory.decodeResource(getResources(),R.drawable.hmmo);
    	Bitmap j98 = BitmapFactory.decodeResource(getResources(),R.drawable.jwo);
    	Bitmap j99 = BitmapFactory.decodeResource(getResources(),R.drawable.kko);
    	Bitmap j100 = BitmapFactory.decodeResource(getResources(),R.drawable.kmo);
    	Bitmap j101 = BitmapFactory.decodeResource(getResources(),R.drawable.kwo);
    	Bitmap j102 = BitmapFactory.decodeResource(getResources(),R.drawable.llo);
    	Bitmap j103 = BitmapFactory.decodeResource(getResources(),R.drawable.lt1);
    	Bitmap j104 = BitmapFactory.decodeResource(getResources(),R.drawable.dvo);
    	Bitmap j105 = BitmapFactory.decodeResource(getResources(),R.drawable.nko);
    	Bitmap j106 = BitmapFactory.decodeResource(getResources(),R.drawable.ngho);
    	Bitmap j107 = BitmapFactory.decodeResource(getResources(),R.drawable.ccho);

    	Bitmap j109 = BitmapFactory.decodeResource(getResources(),R.drawable.bdho);
    	Bitmap j110 = BitmapFactory.decodeResource(getResources(),R.drawable.bjo);
    	Bitmap j111 = BitmapFactory.decodeResource(getResources(),R.drawable.ndoo);
    	Bitmap j112 = BitmapFactory.decodeResource(getResources(),R.drawable.ndho);
    	Bitmap j113 = BitmapFactory.decodeResource(getResources(),R.drawable.nto12);
    	Bitmap j114 = BitmapFactory.decodeResource(getResources(),R.drawable.mno);
    	Bitmap j115 = BitmapFactory.decodeResource(getResources(),R.drawable.mfo);
    	Bitmap j116 = BitmapFactory.decodeResource(getResources(),R.drawable.two);
    	
    	//
    	
    	
    	// add alpha to array list
    	
    	bitmapArray.add(b1);
        bitmapArray.add(b2);
        bitmapArray.add(b3);
        bitmapArray.add(b4);
        bitmapArray.add(b5);
        bitmapArray.add(b6);
        bitmapArray.add(b7);
        bitmapArray.add(b8);
        bitmapArray.add(b9);
        bitmapArray.add(b10);
        bitmapArray.add(b11);
        bitmapArray.add(b12);
        bitmapArray.add(b13);
        bitmapArray.add(b14);
        bitmapArray.add(b15);
        bitmapArray.add(b16);
        bitmapArray.add(b17);
        bitmapArray.add(b18);
        bitmapArray.add(b19);
        bitmapArray.add(b20);
        bitmapArray.add(b21);
        bitmapArray.add(b22);
        bitmapArray.add(b23);
        bitmapArray.add(b24);
        bitmapArray.add(b25);
        bitmapArray.add(b26);
        bitmapArray.add(b27);
        bitmapArray.add(b28);
        bitmapArray.add(b29);
        bitmapArray.add(b30);
        bitmapArray.add(b31);
        bitmapArray.add(b32);
        bitmapArray.add(b33);
        bitmapArray.add(b34);
        bitmapArray.add(b35);
        bitmapArray.add(b36);
        bitmapArray.add(b37);
        bitmapArray.add(b38);
        bitmapArray.add(b39);
        bitmapArray.add(b40);
        bitmapArray.add(b41);
        bitmapArray.add(b42);
        bitmapArray.add(b43);
        bitmapArray.add(b44);
        bitmapArray.add(b45);
        bitmapArray.add(b46);
        bitmapArray.add(b47);
        bitmapArray.add(b48);
        bitmapArray.add(b49);
        bitmapArray.add(b50);
        bitmapArray.add(b51);
        bitmapArray.add(b52);
     
        bitmapArray.add(b54);
        
        bitmapArray.add(a);
        bitmapArray.add(b);
        bitmapArray.add(c);
        bitmapArray.add(d);
        bitmapArray.add(e);
        bitmapArray.add(f);
        bitmapArray.add(g);
        bitmapArray.add(h);
        bitmapArray.add(i);
        bitmapArray.add(j);
        bitmapArray.add(k);
        bitmapArray.add(l);
        bitmapArray.add(m);
        bitmapArray.add(n);
        bitmapArray.add(o);
        bitmapArray.add(p);
        bitmapArray.add(q);
        bitmapArray.add(r);
        bitmapArray.add(s);
        bitmapArray.add(t);
        bitmapArray.add(u);
        bitmapArray.add(v);
        bitmapArray.add(w);
        bitmapArray.add(x);
        bitmapArray.add(y);
        bitmapArray.add(z);
        bitmapArray.add(A);
        bitmapArray.add(B);
        bitmapArray.add(C);
        bitmapArray.add(D);
        bitmapArray.add(E);
        bitmapArray.add(F);
        bitmapArray.add(G);
        bitmapArray.add(H);
        bitmapArray.add(I);
        bitmapArray.add(J);
        bitmapArray.add(K);
        bitmapArray.add(L);
        bitmapArray.add(M);
        bitmapArray.add(N);
        bitmapArray.add(O);
        bitmapArray.add(P);
        bitmapArray.add(Q);
        bitmapArray.add(RR);
        bitmapArray.add(S);
        bitmapArray.add(T);
        bitmapArray.add(U);
        bitmapArray.add(V);
        bitmapArray.add(W);
        bitmapArray.add(X);
        bitmapArray.add(Y);
        bitmapArray.add(Z);
        bitmapArray.add(space);
        bitmapArray.add(space);
    	
    	
    	
    	//
       
        
        // jukto image -> bitmap array 
        bitmapArrayJ.add(j1);
        bitmapArrayJ.add(j2);
        bitmapArrayJ.add(j3);
        bitmapArrayJ.add(j4);
        bitmapArrayJ.add(j5);
        bitmapArrayJ.add(j6);
        bitmapArrayJ.add(j7);
        bitmapArrayJ.add(j8);
        bitmapArrayJ.add(j9);
        bitmapArrayJ.add(j10);
        bitmapArrayJ.add(j11);
        bitmapArrayJ.add(j12);
        bitmapArrayJ.add(j13);
        bitmapArrayJ.add(j14);
        bitmapArrayJ.add(j15);
        bitmapArrayJ.add(j16);
        bitmapArrayJ.add(j17);
        bitmapArrayJ.add(j18);
        bitmapArrayJ.add(j19);
        bitmapArrayJ.add(j20);
        bitmapArrayJ.add(j21);
        bitmapArrayJ.add(j22);
        bitmapArrayJ.add(j23);
        bitmapArrayJ.add(j24);
        bitmapArrayJ.add(j25);
        bitmapArrayJ.add(j26);
        bitmapArrayJ.add(j27);
        bitmapArrayJ.add(j28);
        bitmapArrayJ.add(j29);
        bitmapArrayJ.add(j30);
        bitmapArrayJ.add(j31);
        bitmapArrayJ.add(j32);
        bitmapArrayJ.add(j33);
        bitmapArrayJ.add(j34);
        bitmapArrayJ.add(j35);
        bitmapArrayJ.add(j36);
        bitmapArrayJ.add(j37);
        bitmapArrayJ.add(j38);
        bitmapArrayJ.add(j39);
        bitmapArrayJ.add(j40);
        bitmapArrayJ.add(j41);
        bitmapArrayJ.add(j42);
        bitmapArrayJ.add(j43);
        bitmapArrayJ.add(j44);
        bitmapArrayJ.add(j45);
        bitmapArrayJ.add(j46);
        bitmapArrayJ.add(j47);
        bitmapArrayJ.add(j48);
        bitmapArrayJ.add(j49);
        bitmapArrayJ.add(j50);
        bitmapArrayJ.add(j51);
        bitmapArrayJ.add(j52);
        bitmapArrayJ.add(j53);
        bitmapArrayJ.add(j54);
        bitmapArrayJ.add(j55);
        bitmapArrayJ.add(j56);
        bitmapArrayJ.add(j57);
        bitmapArrayJ.add(j58);
        bitmapArrayJ.add(j59);
        bitmapArrayJ.add(j60);
        bitmapArrayJ.add(j61);
        bitmapArrayJ.add(j62);
        bitmapArrayJ.add(j63);
        bitmapArrayJ.add(j64);
      
        bitmapArrayJ.add(j66);
        bitmapArrayJ.add(j67);
        bitmapArrayJ.add(j68);
        bitmapArrayJ.add(j69);
        bitmapArrayJ.add(j70);
        
       
        bitmapArrayJ.add(j73);
        
        bitmapArrayJ.add(j75);
        bitmapArrayJ.add(j76);
        bitmapArrayJ.add(j77);
        bitmapArrayJ.add(j78);
        bitmapArrayJ.add(j79);
        bitmapArrayJ.add(j80);
        bitmapArrayJ.add(j81);
        bitmapArrayJ.add(j82);
        bitmapArrayJ.add(j83);
        bitmapArrayJ.add(j84);
        bitmapArrayJ.add(j85);
        bitmapArrayJ.add(j86);
        bitmapArrayJ.add(j87);
        bitmapArrayJ.add(j88);
        bitmapArrayJ.add(j89);
        bitmapArrayJ.add(j90);
        bitmapArrayJ.add(j91);
        bitmapArrayJ.add(j92);
        bitmapArrayJ.add(j93);
        bitmapArrayJ.add(j94);
        bitmapArrayJ.add(j95);
        bitmapArrayJ.add(j96);
        bitmapArrayJ.add(j97);
        bitmapArrayJ.add(j98);
        bitmapArrayJ.add(j99);
        bitmapArrayJ.add(j100);
        bitmapArrayJ.add(j101);
        bitmapArrayJ.add(j102);
        bitmapArrayJ.add(j103);
        bitmapArrayJ.add(j104);
        bitmapArrayJ.add(j105);
        bitmapArrayJ.add(j106);
        bitmapArrayJ.add(j107);
     
        bitmapArrayJ.add(j109);
        bitmapArrayJ.add(j110);
        bitmapArrayJ.add(j111);
        bitmapArrayJ.add(j112);
        bitmapArrayJ.add(j113);
        bitmapArrayJ.add(j114);
        bitmapArrayJ.add(j115);
        bitmapArrayJ.add(j116);
        bitmapArrayJ.add(space);
        bitmapArrayJ.add(space);
        // jukto image -> bitmap array


      
        
  
     
        setBackgroundColor(Color.DKGRAY);    
        setTextColor(Color.WHITE);
       
       
       setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/bangla.ttf"));    
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 90);
        setLayoutParams(lp);
        //setTextSize(17);
    
        
        setHorizontalFadingEdgeEnabled(true);
        setWillNotDraw(false);
        setHorizontalScrollBarEnabled(true);
        setVerticalScrollBarEnabled(true);
        setMovementMethod(new ScrollingMovementMethod());
        //setMaxLines(1);
    }
    
    /**
     * A connection back to the service to communicate with the text field
     * @param listener
     */
    public void setService(SoftKeyboard listener) {
    } 


    public void setSuggestions(List<String> suggestions, boolean completions,
            boolean typedWordValid) {
    }
   
    public Bitmap putOverlay(Bitmap bmp1, Bitmap overlay) {
    	Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmOverlay);
        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
 
        	 canvas.drawBitmap(bmp1, 0, 0, null); 
        
        if(bmp1.equals(i_kar))
        	canvas.drawBitmap(overlay, (float) 1, 0, null);   
        else 
        	canvas.drawBitmap(overlay, 0, 0, null);       
     
        return bmOverlay;
    } 
  
    

    public void update(String txt){  	
    	if(txt!=null && txt !="")
    	{
    		
    		 
    	String kn = b_alpha.toString();
    	int in = kn.indexOf("ই");
    	 
    	  //("pe","dhukse txt = "+txt+txt.length());

//can be used for char watching    	  


    	  StringBuilder sb = new StringBuilder(txt);

    	
    	 
// jofla -> singchar replacement
  
    	 sb=chng (txt, "‍্য","▫");
         txt=sb.toString();  
         //("se","hsnto jo am here aft txt="+txt);
    	    	 
// jofla -> singchar replacement    	
    	 
// rofla -> sinchar replacement
        sb=chng (txt, "্র","▶");
        txt=sb.toString();
        //("se","am here aft txt="+txt);
        
    	 
// rofla -> sinchar replacement
        
     // ref -> sinchar replacement
        sb=chng (txt, "র্","▴");
        txt=sb.toString();       
       
    	 
// ref -> sinchar replacement

        
        
//jukto -> single char replacement
    	 for(int j=0;j<jukto.length;j++)
    	 {    		
    		 sb = chng(txt,jukto[j],juktor[j]);
    		 txt=sb.toString();
    	 }
//ref-> single char replacement
    	
          //("se","am here aft txt="+txt);   	 
//bringing all ে , ৈ  to front 
       		 
    	 	sb = shifting(txt, "ে");
        	txt = sb.toString();
        	sb = shifting(txt, "ৈ");
        	txt = sb.toString();
        	sb = shifting(txt, "▴"); //ref
        	txt = sb.toString();
        	  //("se","am here aft txt2="+txt);   
        	 
        
//bringing all ে , ৈ to front    	 

        	
//balancing  ো , ৌ       	
        	sb = shiftingn(txt, "ো");
        	txt = sb.toString();
        	sb = shiftingn(txt, "ৌ");
        	txt = sb.toString();
//balancing  ো , ৌ            	

        	
//text -> image replacing starts here
        	
         SpannableStringBuilder ssb = new SpannableStringBuilder(txt);
//image replacing for space    
         
//image replacing for space   
         ssb = replace_same(ssb,txt,",",coma);
         ssb = replace_same(ssb,txt,"।",dari);
         ssb = replace_same(ssb,txt,"?",que);  
         ssb = replace_same(ssb,txt," ",space);  
         ssb = replace_same(ssb,txt,"১",z11);
         ssb = replace_same(ssb,txt,"২",z22);
         ssb = replace_same(ssb,txt,"৩",z33);
         ssb = replace_same(ssb,txt,"৪",z44);
         ssb = replace_same(ssb,txt,"৫",z55);
         ssb = replace_same(ssb,txt,"৬",z66);
         ssb = replace_same(ssb,txt,"৭",z77);
         ssb = replace_same(ssb,txt,"৮",z88);
         ssb = replace_same(ssb,txt,"৯",z99);
         ssb = replace_same(ssb,txt,"০",z00);
         ssb = replace_same(ssb,txt,"1",z1);  
         ssb = replace_same(ssb,txt,"2",z2);  
         ssb = replace_same(ssb,txt,"3",z3);  
         ssb = replace_same(ssb,txt,"4",z4);  
         ssb = replace_same(ssb,txt,"5",z5);  
         ssb = replace_same(ssb,txt,"6",z6);  
         ssb = replace_same(ssb,txt,"7",z7);  
         ssb = replace_same(ssb,txt,"8",z8);  
         ssb = replace_same(ssb,txt,"9",z9);  
         ssb = replace_same(ssb,txt,"0",z0);  
         
     
         
        
//For single chars
    	 

    	 for(int r=0;r<b_alpha.length;r++)
        	 {
        		 ssb = single_char(ssb,txt,b_alpha[r],bitmapArray.get(r)); 
        		 ssb = single_overlay(ssb,txt,b_alpha[r],bitmapArray.get(r));        		
        		 ssb = double_overlay(ssb,txt,b_alpha[r],bitmapArray.get(r));
        		arrays.clear();
        		arrayo.clear();
        		
         	 }
	
//For single chars         
       	 
//For jukto
         for(int jr=0;jr<juktor.length;jr++)
    	 {  
    		 ssb = single_char(ssb,txt,juktor[jr],bitmapArrayJ.get(jr)); 
    		 ssb = single_overlay(ssb,txt,juktor[jr],bitmapArrayJ.get(jr));
    		 ssb = double_overlay(ssb,txt,juktor[jr],bitmapArrayJ.get(jr));
    		 arrays.clear();
     		 arrayo.clear();
     	 }
//For jukto
    	 
    	 ssb = ekaki_kar(ssb,txt,"ি",i_kar); 
    	 ssb = ekaki_kar(ssb,txt,"ু",hrosu);     
         ssb = ekaki_kar(ssb,txt,"ূ",dirghu); 
         ssb = ekaki_kar(ssb,txt,"ৃ",hri); 
         ssb = ekaki_kar(ssb,txt,"ৗ",oi); 
         ssb = ekaki_kar(ssb,txt,"ী",dirghi); 
         ssb = ekaki_kar(ssb,txt,"্",hsnto); 
         //("se","am here aft ssb="+ssb);
         ssb = ekaki_kar(ssb,txt,"▶",rofla);
         ssb = ekaki_kar(ssb,txt,"▴",ref); 
         ssb = ekaki_kar(ssb,txt,"ৗ",ou1); 
          
         
        		 
//For single chars         
  	 
         setText(null);  	 
	    setText(ssb);
	  //  ssb = null;
	  
    }
    	else
    		  setText(null);  
    }     
 
    //new technology jukto -> single char replacement
    
    public StringBuilder chng(String txt,String ch,String nch)
    {
    	 //("se","chng here aft txt="+txt+" ch="+ch+" nch="+nch);
        	StringBuilder sx = new StringBuilder();       	
        	sx.append(txt);
        	int ofe = sx.indexOf(ch,0);   
        	
        	 //("pe","am here of ofe1 ="+ofe+"sx ="+sx);
    for(int ofs=0;ofs<txt.length() && ofe!=-1;ofs=ofe+1)
    {   	
     	   
    	
      		  ofe = sx.indexOf(ch,ofs);   
      		  if(ofe == -1)
      			  break;
      		 //("pe","am here of ofe2 ="+ofe+"sx ="+sx);
     		  sx.replace(ofe, ofe+ch.length(), nch);
     			
     
    }
     return sx;   	
    }

    //new technology jukto -> single char replacement

    //e_kar replacing
    public StringBuilder shifting(String txt,String ch)
    {
        	StringBuilder sx = new StringBuilder();
        	
        	sx.append(txt);
        	int ofe = sx.indexOf(ch,0);    	
    for(int ofs=0;ofs<txt.length() && ofe!=-1;ofs=ofe+1)
    {   	
     	   
      		  ofe = sx.indexOf(ch,ofs);
     
     if(ofe!=-1)
     {
     if(ch == "▴")
     {
    	 if(ofe !=txt.length()-1)
    	 {
    		 if((""+txt.charAt(ofe+1)).compareTo(" ")!=0)
    	      {
    			 sx.replace(ofe, ofe+2, sx.charAt(ofe+1)+ch);
    	      }
    	 } 
    		 ofe=ofe+1;
     }
     
         
     else
     {
    	 if(ofe==0)
    	 {
    		 ;
    	 }
    	 else if(sptype.contains(""+sx.charAt(ofe-1)))
    	 {
    		 ;
    	 }
    		 else
    	 sx.replace(ofe-1, ofe+1, ch+sx.charAt(ofe-1));
     }	
     }
     else
    	 break;
     
    }
    //("re","replaced return sx = "+sx); 
    return sx;   	
    }
    
    //e_replacing
    
   //o_kar replacing
    public StringBuilder shiftingn(String txt,String ch)
    {
    	String chn="";
    	if(ch == "ো")
    	{
    		chn = "ে"+"া";
    	}
    	else if(ch == "ৌ")
    	{
    		chn = "ে"+"ৗ";
    	}
        	StringBuilder sx = new StringBuilder();
        	sx.append(txt);
        	int ofe = sx.indexOf(ch,0);    	
    for(int ofs=0;ofs<txt.length() && ofe!=-1;ofs=ofe+1)
    {   	
     	   
      		  ofe = sx.indexOf(ch,ofs);
     
     if(ofe!=-1)
     {
    	 if(ofe==0)
    	 {
    		 sx.replace(ofe, ofe+1,""+chn.charAt(0)+""+chn.charAt(1));;
    	 }
    	 else if(sptype.contains(""+sx.charAt(ofe-1)))
    	 {
    		 sx.replace(ofe, ofe+1,""+chn.charAt(0)+""+chn.charAt(1));
    	 }
    		 else
    		 {
     		sx.replace(ofe-1, ofe+1,""+chn.charAt(0)+sx.charAt(ofe-1)+chn.charAt(1));
     		txt = sx.toString();
    		 }
     	
     }
     else
    	 break;
     
    }
     return sx;   	
    }
    
    //o_kar replacing
    
    
    
 


    public SpannableStringBuilder replace_same(SpannableStringBuilder ssb,String txt,String c,Bitmap b)
    {
   	
    	
    	 int ofe = txt.indexOf(c,0); 
    	   for(int ofs=0;ofs<txt.length() && ofe!=-1;ofs++)
    	   {
    		 
    		   ofe = txt.indexOf(c,ofs); 
    		   if(ofe == -1)
    			   break;
    		
    			   ssb.setSpan( new ImageSpan( b ), ofe, ofe+c.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE); 
        			      	
    	   }
    	  
		return ssb;
    	
    }    

    
 
    
 
    //আমি একলা কার   
    public SpannableStringBuilder ekaki_kar(SpannableStringBuilder ssb,String txt,String c,Bitmap b)
    {
   	
    	
    	 int ofe = txt.indexOf(c,0); 
    	   for(int ofs=0;ofs<txt.length() && ofe!=-1;ofs++)
    	   {
    		 
    		   ofe = txt.indexOf(c,ofs); 
    		   if(ofe == -1)
    			   break;
    		
    		   if(ofe==0)
    		   {
    			   ssb.setSpan( new ImageSpan( b ), ofe, ofe+c.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);    
    			   
    		   }
    		   else if(txt.length()==2 &&ofe==1)
    		   {
    			   if(kar.indexOf(txt.charAt(ofe-1)) !=-1 || sptype.contains(""+txt.charAt(ofe-1))   )
    			   {
    				   ssb.setSpan( new ImageSpan( b ), ofe, ofe+c.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);  
    			   }
    			   
    		   }
    		   else if(ofe==txt.length()-1)
    		   {
    			     if((kar.indexOf(txt.charAt(ofe-1)) !=-1 || sptype.contains(""+txt.charAt(ofe-1)) ) && (kar.indexOf(txt.charAt(ofe-2)) !=-1 || sptype.contains(""+txt.charAt(ofe-2))   ))
    			   {
    				   ssb.setSpan( new ImageSpan( b ), ofe, ofe+c.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);  
    			   }
    		// ekhane edit krsi -fahim
    			     else if(((""+txt.charAt(ofe-1)).compareTo(" ")==0))
    			   {
    				   ssb.setSpan( new ImageSpan( b ), ofe, ofe+c.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);  
    			   
    			   }
    			     //
    		   }
    		   else
    		   {
if((kar.indexOf(txt.charAt(ofe-1)) !=-1 || sptype.contains(""+txt.charAt(ofe-1))) && (kar.indexOf(txt.charAt(ofe-2)) !=-1 || sptype.contains(""+txt.charAt(ofe-2))))
    			   {
    				   ssb.setSpan( new ImageSpan( b ), ofe, ofe+c.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);  
    			   } 
    			   
    		   }
    		   
    		   
    	   }
    	  
		return ssb;
    	
    }    
       
    
    //আমি একাকী কার   
    
    
    
    
    // single character
    
    
    public SpannableStringBuilder single_char(SpannableStringBuilder ssb,String txt,String c,Bitmap b)
    {
   	
    	
    	 int ofe = txt.indexOf(c,0); 
    	   for(int ofs=0;ofs<txt.length() && ofe!=-1;ofs=ofe+1)
    	   {
    		 
    		   ofe = txt.indexOf(c,ofs); 
    		   if(ofe == -1)
    			   break;
    		
    		   if(txt.length()==1)
    		   { // ("haga", ""+txt.charAt(ofe));
    			  arrays.add(ofe);
    			   ssb.setSpan( new ImageSpan( b ), ofe, ofe+c.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);    
    			   
    		   }
    		   else if(txt.length()==2)
    		   {
    			   if(ofe==1)
    			   {
    			
    				   ssb.setSpan( new ImageSpan( b ), ofe, ofe+c.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);  
    				   arrays.add(ofe);
    			   } 
    			   else if(ofe==0)
    			   {
    			       if((kar.indexOf(txt.charAt(ofe+1)) ==-1 || sptype.contains(""+txt.charAt(ofe+1))) )
    				   {ssb.setSpan( new ImageSpan( b ), ofe, ofe+c.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);  
    				   arrays.add(ofe);
    				   }
    				   }   			  
    			    
    		   }
    		   else if(ofe==txt.length()-1)
    		   {
    			   
    				   ssb.setSpan( new ImageSpan( b ), ofe, ofe+c.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE); 
    				   arrays.add(ofe);
    			   
    		   }
    		   else
    		   {
    			   if((kar.indexOf(txt.charAt(ofe+1)) ==-1 || sptype.contains(""+txt.charAt(ofe+1))) )
    			   {
    				   ssb.setSpan( new ImageSpan( b ), ofe, ofe+c.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);  
    				   arrays.add(ofe);
    			   } 
    			   
    		   }
    		   
    		   
    	   }
    	  
		return ssb;
    	
    }    
    
    
    //single character
    
    
    //single overlay
    public SpannableStringBuilder single_overlay(SpannableStringBuilder ssb,String txt,String ch, Bitmap alp)
    {
 	   //("hagu","am here text="+txt);
 	   Bitmap krsi = null;
 	//  //("muti","sm of so="+sm);
        	int ofe = txt.indexOf(ch,0);    	
    for(int ofs=0;ofs<txt.length() && ofe!=-1;ofs=ofe+1)
    {   	
 	   //("hagu","loop er suru ofs="+ofs);   
      		  ofe = txt.indexOf(ch,ofs);
     
     if(ofe!=-1)
     {
    	// //("hagu","sm = "+sm);
     	if(!arrays.contains(ofe))
     	{  	
     		//("new","Found ko at ofe ="+ofe);
     		
     		if (ofe== txt.length()-2 &&kar.indexOf(txt.charAt(ofe+1)) !=-1 )
     		{////("hagu","1mane ami asci cofe="+txt.charAt(ofe)+"cofe+1="+txt.charAt(ofe+1)+"cofe2="+txt.charAt(ofe+2));
     			krsi = putOverlay(bitmapKar.get(kar.indexOf(txt.charAt(ofe+1))),alp);  		
 				ssb.setSpan( new ImageSpan( krsi ), ofe, ofe+2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); 	
 				arrayo.add(ofe);
     			
     		}
     		else if( (kar.indexOf(txt.charAt(ofe+1)) !=-1 && ((kar.indexOf(txt.charAt(ofe+2)) ==-1)))|| ( kar.indexOf(txt.charAt(ofe+1)) !=-1 && sptype.contains(""+txt.charAt(ofe+2))))
     		{  //("hagu","2mane ami asci cofe="+txt.charAt(ofe)+"cofe+1="+txt.charAt(ofe+1)+"cofe2="+txt.charAt(ofe+2));
     			//("f", "ofe= "+ txt.charAt(ofe)+"ofe+1= "+ txt.charAt(ofe+1)+ " ofe+2= "+txt.charAt(ofe+2));
     			krsi = putOverlay(bitmapKar.get(kar.indexOf(txt.charAt(ofe+1))),alp);  		
 				ssb.setSpan( new ImageSpan( krsi ), ofe, ofe+2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); 	
 				arrayo.add(ofe);
     			
     		}
     		    		    		
     	}
     
     }
     else
     	break;   
    }
    
    return ssb;
    }
    //single overlay
    
    
    //double overlay
    public SpannableStringBuilder double_overlay(SpannableStringBuilder ssb,String txt,String ch, Bitmap alp)
    {
 	   //("pe","am here"+txt);

 	   
        	int ofe = txt.indexOf(ch,0);    	
    for(int ofs=0;ofs<txt.length() && ofe!=-1;ofs=ofe+1)
    {   	
 	   //("pe","loop er suru ofs="+ofs);   
      		  ofe = txt.indexOf(ch,ofs);
     
     if(ofe!=-1) 
     {
     	if(!arrays.contains(ofe) && !arrayo.contains(ofe))
     	{  	
     		//("new","Found ko at ofe ="+ofe);
     		
     		if(kar.indexOf(txt.charAt(ofe+1)) !=-1 && kar.indexOf(txt.charAt(ofe+2)) !=-1)
     		{
     			 
     			
    			 if(kar.indexOf(txt.charAt(ofe+1)) ==kar.indexOf(txt.charAt(ofe+2)))
     			{
    				 Bitmap ovl1 = putOverlay(bitmapKar.get(kar.indexOf(txt.charAt(ofe+1))),alp);   
    				 ssb.setSpan( new ImageSpan( ovl1 ), ofe, ofe+2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); 
    				 ssb.setSpan( new ImageSpan( bitmapKar.get(kar.indexOf(txt.charAt(ofe+2))) ), ofe+2, ofe+3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); 
     			}
     			else
     			{
     				Bitmap ovl1 = putOverlay(bitmapKar.get(kar.indexOf(txt.charAt(ofe+1))),alp);       		
       			 Bitmap ovl2 = putOverlay(bitmapKar.get(kar.indexOf(txt.charAt(ofe+2))),ovl1);
     			  ssb.setSpan( new ImageSpan( ovl2 ), ofe, ofe+3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); 
     			}
     		}
     		    		    		
     	}
     
     }
     else
     	break;   
    }
    
    return ssb;
    }
    //double overlay
 
    
    
    public void clear() {
    }

}
