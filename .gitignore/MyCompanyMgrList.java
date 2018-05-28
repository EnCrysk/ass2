package tdt.algo;
public class MyCompanyMgrList extends CompanyMgrList {	
	private int i,j,s=0;
	
	public MyCompanyMgrList()
	{
		super();
	}
	
	/*********************************************************************************************/
	// you can add some more methods if you need.
	
	private int getEventCode(eventList pEvent)
	{
		// code here
		if(pEvent.getnEventCode()/1000<10)
			return pEvent.getnEventCode()/1000;
		else if(pEvent.getnEventCode()/1000>10)
			return pEvent.getnEventCode()/10000;
		else
			return  pEvent.getnEventCode();
	}

	private int getProductId(int nEvent)
	{
		// code here
		return 0;
	}

	/**
	 * get quantity fron nEventCode of eventList
	 */
	private int getQuan(int nEvent)
	{
		// code here
		return 0;
	}

	/**
	 * add a new noteList add the end of linked-list notesList
	 * tail:  pointer to tail of head of notesList
	 * side-effec: tail point to the new node which is now the tail
	 */
	private void doImport(int nEventCode)
	{
		notesList temp = head;
		while(temp.getNext()!= null){
                temp = temp.getNext();
            }
        j=nEventCode%10000;
        notesList newNode = new notesList(j/10,nEventCode%10,1);
        temp.setNext(newNode);

	}

	/**
	 * add a new noteList add the end of linked-list notesList
	 * tail:  pointer to tail of head of notesList
	 * side-effec: tail point to the new node which is now the tail
	 */
	private void doReserve(int nEventCode)
	{
		notesList temp= head;
		while(temp.getNext()!= null){
                temp = temp.getNext();
            }
		j=nEventCode%10000;
        notesList newNode = new notesList(j/10,nEventCode%10,2);
        temp.setNext(newNode);
	}
	
	/**
	 * stats and delete IMPORTED product with code in nEventCode,
	 * side-effect: head, tail change
	 */
	private void doStatImport(int nEventCode)
	{
		notesList temp= head;
		while(temp.getNext()!= null){
			if(temp.getNext().getnProdID()==(nEventCode%1000))
			{
				s=s+temp.getnQuan();
			}
				temp = temp.getNext();
		}
		if(s==0)
		{
			notesList lastitem= head;
		while(lastitem.getNext()!= null){
                lastitem = lastitem.getNext();
            }
        j=nEventCode%1000;
        notesList newNode = new notesList(j,0,1);
        lastitem.setNext(newNode);
		}
		if(s>0 && s<99){
			j=nEventCode%1000;
        	notesList newNode = new notesList(j,s,1,head);
        	head=newNode;
        	notesList currentItem = head;
            while(currentItem.getNext()!=null){
            	if(currentItem.getNext().getnProdID() == (nEventCode%1000))
            	{
                    while(currentItem.getNext().getnType()==1)
                    {
                    	notesList nextcurr = currentItem.getNext();
                    	currentItem.setNext(nextcurr.getNext());
                    	nextcurr.setNext(null);
                    }
                }
                currentItem = currentItem.getNext();
			}
		}
		if(s>99){
			s=s%100;
			j=nEventCode%1000;
        	notesList newNode = new notesList(j,s,1,head);
        	head=newNode;
        	notesList currentItem = head;
            while(currentItem.getNext()!=null){
            	if(currentItem.getNext().getnProdID() == (nEventCode%1000))
            	{
                    while(currentItem.getNext().getnType()==1)
                    {
                    	notesList nextcurr = currentItem.getNext();
                    	currentItem.setNext(nextcurr.getNext());
                    	nextcurr.setNext(null);
                    }
                }
                currentItem = currentItem.getNext();
			}
		}

	}

	private void doStatReserve(int nEventCode)
	{
		// code here
	}

	private void doStatRest(int nEventCode)
	{
		// code here
	}

	/**
	 *
	 * find Product with Max Rest in storage
	 */

	private void doMaxRestStat()
	{
		// code here
	}

	private void doRemoveInvalidReserve()
	{
		// code here
	}
	
	public boolean checkPalindrome()
	{
		// code here
		return false;
	}

	public void storage (notesList theFirst, eventList pEvent)
	{
		// code here
		head = new notesList();
		head.setnProdID(theFirst.getnProdID());
		head.setnQuan(theFirst.getnQuan());
		head.setnType(theFirst.getnType());
		head.setNext(null);
		tail = head;

		while(pEvent != null)
		{
			switch(getEventCode(pEvent))
			{
				case eventList.TERMINATE_EVENT:
					return;
				case eventList.IMPORT_EVENT:
					doImport(pEvent.getnEventCode());
					break;
				case eventList.RESERVED_EVENT:
					doReserve(pEvent.getnEventCode());
					break;
				case eventList.STAT_IMPORT_EVENT:
					doStatImport(pEvent.getnEventCode());
					break;
				case eventList.STAT_RESERVED_EVENT:
					doStatReserve(pEvent.getnEventCode());
					break;
				case eventList.STAT_REST_EVENT:
					doStatRest(pEvent.getnEventCode());
					break;
				case eventList.MAX_REST_STAT_EVENT:
					doMaxRestStat();
					break;
				case eventList.REMOVE_INVALID_RESERVE_EVENT:
					doRemoveInvalidReserve();
					break;
				default:
			}

			pEvent = pEvent.getNext();

			if(head == null)
				break;
		}
	}
}