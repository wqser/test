package datastructure;

/**
 * Created by wqs on 2017/4/24.
 */


import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

    /**
     * DOM方式解析xml
     */
    public class xmltest {

        public static void main(String[] args) {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //2、创建一个DocumentBuilder的对象
            try {
                //创建DocumentBuilder对象
                DocumentBuilder db = dbf.newDocumentBuilder();
                //3、通过DocumentBuilder对象的parser方法加载books.xml文件到当前项目下
            /*注意导入Document对象时，要导入org.w3c.dom.Document包下的*/
                Document document = db.parse("E:/data/data/TU-26E/ErrorInfo.xml");//传入文件名可以是相对路径也可以是绝对路径
                //获取所有book节点的集合
                NodeList errorlist = document.getElementsByTagName("Error");
                //通过nodelist的getLength()方法可以获取bookList的长度
                System.out.println("一共有" + errorlist.getLength() + "Error");
                Node error = errorlist.item(0);

                NodeList itemLsit = error.getChildNodes();
//                for(int i= 1;i<itemLsit.getLength();i=i+2){
//                   Node itemChildNodeName  =  itemLsit.item(i);
//                    System.out.println("第"+i+"个子节点："+itemChildNodeName.getNodeName());
//                }
                System.out.println("Error下有" + itemLsit.getLength() + "个子节点");
                int i = 1;

                //遍历每一个book节点
                while (i < itemLsit.getLength()) {
                    System.out.println("=================下面开始遍历第" + (i + 1) + "本书的内容=================");
                    //❤未知节点属性的个数和属性名时:
                    //通过 item(i)方法 获取一个book节点，nodelist的索引值从0开始
                    Node item = itemLsit.item(i);
                    //获取book节点的所有属性集合
                    NamedNodeMap attrs = item.getAttributes();
                    System.out.print("item有" + attrs.getLength() + "属性");

                    //遍历book的属性
                    Node errorCode = attrs.getNamedItem("ErrCode");
                    //获取属性名

                    System.out.print("属性名：" + errorCode.getNodeName());
                    //获取属性值
                    System.out.println("--属性值" + errorCode.getNodeValue());
                    if (errorCode.getNodeValue().equals("0199")) {
                        NodeList textList = item.getChildNodes();
                        NodeList valuelist = textList.item(1).getChildNodes();
                        if(valuelist.getLength()==0){

                            break;
                        }
                        Node value = valuelist.item(1);
                        NamedNodeMap erroeMap = value.getAttributes();
                        System.out.print(erroeMap.getNamedItem("Value").getNodeValue());

                    }
                    i = i + 2;
                }

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String getErrorName(String falutCode,String type) {
            String errorName="";
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder db = dbf.newDocumentBuilder();

                Document document = db.parse("E:/ErrorInfo.xml");

                NodeList errorlist = document.getElementsByTagName("Process");

                System.out.println("一共有" + errorlist.getLength() + "Process");
                Node error = errorlist.item(0);

                NodeList itemLsit = error.getChildNodes();
                System.out.println("process下有" + itemLsit.getLength() + "个子节点");
                int i = 1;
                while (i < itemLsit.getLength()) {
                    System.out.println("=================下面开始遍历第" + (i + 1) + "本书的内容=================");
                    Node item = itemLsit.item(i);
                    NamedNodeMap attrs = item.getAttributes();
                    System.out.print("item有" + attrs.getLength() + "属性");
                    Node errorCode = attrs.getNamedItem("ProcCode");
                    System.out.print("属性名：" + errorCode.getNodeName());
                    System.out.println("--属性值" + errorCode.getNodeValue());
                    if (errorCode.getNodeValue().equals(falutCode)) {
                        NodeList textList = item.getChildNodes();
                        NodeList valuelist = textList.item(3).getChildNodes();
                        if(valuelist.getLength()==0){
                            errorName = "不明错误";
                            break;
                        }
                        Node value = valuelist.item(1);
                        NamedNodeMap erroeMap = value.getAttributes();
                        System.out.print(erroeMap.getNamedItem("Value").getNodeValue());
                        errorName = erroeMap.getNamedItem("Value").getNodeValue();
                        break;
                    }else {
                        errorName = "没找到对应的错误代码";
                        i = i + 2;
                    }

                }

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

           return errorName;
        }
    }
