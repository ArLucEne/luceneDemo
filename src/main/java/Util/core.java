package Util;

import Search.Searcher;
import Util.Menu;

import java.util.ArrayList;


/**
 * 根据多个食材返回不同菜品方案
 */
public class core {

    /**
     * 字符串处理，提取出多个食材
     * @param materials
     * @return Arraylist
     */
    public static ArrayList IndexMaterial(String materials){
        //materials="土豆 番茄 洋葱 五花肉 菠菜 色拉 白菜";
        int locatestart=0;
        int locatend=0;
        ArrayList<String > StringLIst=new ArrayList();
        //若字符串以空格结尾，会影响最终结果
        //将最后的空格删除
        if(materials.endsWith(" ")){
            materials=materials.substring(0,materials.length()-1);
        }
        //将空格间的字符串提取出来
        while(true){
            locatend=materials.indexOf(" ",locatestart);
            if(locatend<0){
                StringLIst.add(materials.substring(locatestart,materials.length()));
                break;
            }
            StringLIst.add(materials.substring(locatestart,locatend));
            locatestart=locatend+1;
        }

        return StringLIst;
    }

    /**
     * 将不同食材匹配多个搜索的查询关键词
     * @param materials
     * @return plan
     */
    public static ArrayList distribute(String materials){
        ArrayList<String> StringList=IndexMaterial(materials);
        int i=0;
        int j=0;
        int k=0;
        int length=StringList.size();
        //提取不同食材数量的匹配方案
        ArrayList<String> plan=new ArrayList();
        for(i=2;i<=length/2;i++){
            for(j=0;j<length;j+=i){
                String e="";
                if(length-j>i){
                    for( k=0;k<i;k++){
                        e=e+StringList.get(j+k)+"+";
                        if(length-j-k<=2){
                            e+=StringList.get(length-1);
                            j+=i;
                            continue;
                        }
                    }
                }else{
                    for(k=j;k<length;k++){
                        e=e+StringList.get(k)+"+";
                    }
                }
                plan.add(e);
            }
        }
        //提取前后为一组的方案
        for(i=0;i<=length/2;i++){
            if(i<length/2) {
                String e = "";
                e = e + StringList.get(length - i-1) + "+";
                e = e + StringList.get(i) + "+";
                plan.add(e);
            }else {
                String e=StringList.get(i);
                plan.add(e);
            }
        }
        //所有食材构成一个方案
        String m="";
        for(String e:StringList){
            m=m+e+"+";
        }
        plan.add(m);

        return plan;
    }

    /**
     * 对每个匹配结果查询，获取查询结果前五的食谱，组成五个方案
     * @param materials
     * @return plans
     */

    public static ArrayList getPlans(String materials){
        ArrayList<String> plan=distribute(materials);
        ArrayList<Menu> plans=new ArrayList();
        for(String e:plan){
            ArrayList<Menu> menus= Searcher.search(e);

            if(menus.size()<5){
                for(Menu m:menus){
                    plans.add(m);
                }

            }else{
                for(int l=0;l<5;l++){
                    Menu m=menus.get(l);
                    plans.add(m);
                }

            }
        }

        ArrayList<Menu> finalPlans=new ArrayList();
        for(int i=0;i<5;i++){
            finalPlans.add(new Menu());
            for(int j=i;j<plans.size();j+=5){
                finalPlans.add(plans.get(j));
            }

        }
     System.out.println("==================");
        for(Menu e:finalPlans){
            System.out.println(e.getName());
        }
        System.out.println(finalPlans.size());

    return finalPlans;
    }

    public static String exchangeSteps(String steps){

        return  steps.replaceAll(" ","\n");


    }
/*
    public static void main(String[] args){
        //getPlans("土豆 番茄 洋葱 五花肉 菠菜 芹菜 鸡蛋");
        exchangeSteps("土豆 番茄 洋葱 五花肉 菠菜 芹菜 鸡蛋");
    }
*/
}
