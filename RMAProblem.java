public class RMAProblem
{
	String prodNo; //product number
	String failedObj1; //failed object 1
	String ntfCode;	 //ntf code 
	String compPart; // juniper component part
	String serialNo; //serial number
	String rmaDetailNo; //rma detail number
	String srCustomer;

	/*constructor for a MRRProblem object
	 *@param String p: product number of the RMA
	 *@param String f: failed object of the RMA
	 *@param String n: ntf code of the RMA, if RMA is not NTF this will be blank
	 *@param String c: juniper component part
	 *@param String s: serial number 
	 *@param String r: RMA detail number
	 */
	public RMAProblem(String p, String f, String n, String c, String s, String r, String sr)
	{
		prodNo = p;
		failedObj1 = f;
		ntfCode = n;
		compPart = c;
		serialNo = s;
		rmaDetailNo = r;
		srCustomer = sr;
	}

	public RMAProblem()
	{
		prodNo = "pnull";
		failedObj1 = "fnull";
		ntfCode = "nnull";
		compPart = "cnull";
		serialNo = "snull";
		rmaDetailNo = "rnull";
		srCustomer = "srnull";
	}
	
	/*returns details of the RMA
	 *@return String: product number, failed object, NTF code, component part, serial number, RMA detail number
	 */	
	public String toString()
	{
		String rmaProblem = prodNo+", "+failedObj1+", "+ntfCode+", "+compPart+", "+serialNo+", "+rmaDetailNo+", "+srCustomer;
		return rmaProblem;
	}

	/*
	 *returns the product number of the RMA
	 *@return String: product number of the RMA
	 */
	public String getProdNo()
	{
		return prodNo;
	}

	public void setProdNo(String p)
	{
		prodNo = p;
	}
	
	/*
	* returns the failed object of the RMA
	* @return String: failed object
	*/
	public String getFailedObj()
	{
		return failedObj1;
	}

	public void setFailedObj(String f)
	{
		failedObj1 = f;
	}
	
	/*
	* returns the NTF code of the RMA
	* @return String: NTF code
	*/
	public String getNTFCode()
	{
		return ntfCode;
	}

	public void setNTFCode(String n)
	{
		ntfCode = n;
	}
	
	/*
	* returns the juniper component part of the RMA
	* @return String: juniper component part
	*/
	public String getCompPart()
	{
		return compPart;
	}

	public void setCompPart(String c)
	{
		compPart = c;
	}
	
	/*
	* returns the Serial number of the RMA
	* @return String: serial number
	*/
	public String getSerialNo()
	{
		return serialNo;
	}

	public void setSerialNo(String s)
	{
		serialNo = s;
	}
	
	/*
	* returns the RMA detail number
	* @return String: RMA detail number
	*/
	public String getRMADetailNo()
	{
		return rmaDetailNo;
	}

	/*
	 * sets the RMA detail number of the RMAProblem
	 * @param String r: new RMA detail number to give to the RMAProblem
	 */
	public void setRMADetailNo(String r)
	{
		rmaDetailNo = r;
	}
	
	public String getSrCustomer()
	{
		return srCustomer;
	}
	
	public void setSrCustomer(String sr)
	{
		srCustomer = sr;
	}
	
	public static void main(String[] args)
	{
		//testing to make sure object works
		//RMAProblem p1 = new RMAProblem("product","failed","ntf","comp","serial","rma");
		//System.out.println(p1.toString());
		//System.out.println(p1.getProdNo());
	}
}