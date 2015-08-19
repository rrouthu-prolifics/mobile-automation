import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class MailAlert
{

    public MailAlert()
    {
    }

    public static void main(String args[])
        throws IOException
    {
        String resultXmlFilePath = (new StringBuilder(String.valueOf(System.getProperty("java.io.tmpdir")))).append("test-output").append(File.separator).append("testng-results.xml").toString();
        int passPercentage = getPassPercentage(resultXmlFilePath);
        sendMailAlert(passPercentage, args[0], args[1], args[2], args[3], args[4]);
    }

    public static int getPassPercentage(String testngResultXmlPath)
    {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        int totalCount = 0;
        int passCount = 0;
        try
        {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(testngResultXmlPath);
            Element rootElement = document.getDocumentElement();
            NodeList nodeList = rootElement.getChildNodes();
            for(int i = 0; i < nodeList.getLength(); i++)
                if(nodeList.item(i).getNodeName().equalsIgnoreCase("suite"))
                {
                    NodeList suitNodeList = nodeList.item(i).getChildNodes();
                    for(int j = 0; j < suitNodeList.getLength(); j++)
                        if(suitNodeList.item(j).getNodeName().equalsIgnoreCase("test"))
                        {
                            NodeList testNodeList = suitNodeList.item(j).getChildNodes();
                            for(int k = 0; k < testNodeList.getLength(); k++)
                            {
                                Node classNode = testNodeList.item(k);
                                if(classNode.getNodeName().equalsIgnoreCase("class"))
                                {
                                    totalCount++;
                                    NodeList testMethodNodeList = testNodeList.item(k).getChildNodes();
                                    for(int l = 0; l < testMethodNodeList.getLength(); l++)
                                    {
                                        Node testMethodNode = testMethodNodeList.item(l);
                                        if(testMethodNode.getNodeName().equalsIgnoreCase("test-method"))
                                        {
                                            NamedNodeMap map = testMethodNode.getAttributes();
                                            if(map.getNamedItem("is-config") == null && map.getNamedItem("status").getNodeName().equalsIgnoreCase("pass"))
                                                passCount++;
                                        }
                                    }

                                }
                            }

                        }

                }

        }
        catch(ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch(SAXException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        int passPercentage = (passCount * 100) / totalCount;
        return passPercentage;
    }

    public static void sendMailAlert(int passPercentage, String mailAlertVbsFilePath, String selenium, String suiteParameter, String suiteTestName, String emailIds)
        throws IOException
    {
        String command = (new StringBuilder(String.valueOf(mailAlertVbsFilePath))).append(" ").append(selenium).append(" ").append(suiteParameter).append(" ").append(suiteTestName).append(" ").append(emailIds).toString();
        command = "wscript "+ command;
        Runtime.getRuntime().exec(command);
    }
}
