package ink.jasn.ca.basis.helper;

/***************************************************************************************************
 * Packages and Classes Import :
 * @IMPRT----------------------*
 */

import ink.jasn.ca.basis.central.AbstractSyntaxTreeNode;
import ink.jasn.ca.type.base.ASNMaster;
import ink.jasn.ca.type.base.ASNModelViewNode;
import ink.jasn.com.exception.ASNErrorCodes;
import ink.jasn.com.exception.ASNException;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * @Source : SyntaxTreeOrganizer.java
 * @Author : Nirmal Kumar Biswas
 * @Date : Aug 11, 2009
 * @Time : 1:41:06 AM
 * @Version : $0.01
 */
public class ModelSyntaxTreeOrganizer
{
	private List<AbstractSyntaxTreeNode> asnTypeElementList;

	/**
	 * Constructor with ASN.1 NODE list as argument
	 * 
	 * @param asnTypeList
	 */
	public ModelSyntaxTreeOrganizer(List<AbstractSyntaxTreeNode> asnTypeList)
	{
		asnTypeElementList = asnTypeList;
	}

	/**
	 * This method serves the purpose of organizing list of MST-NODES having
	 * first level child. Model syntax tree are also formed here after passing
	 * through few validations phases for a given MST-NODES. The final outcome of
	 * this tree organization is a ASN.1 master ASN.1 type of NODE having all
	 * possible child sub-tree derived from a well defined ASN.1 grammar file.
	 * 
	 * @return
	 * @ASNMaster - the master node from ASN.1 type
	 * @throws Exception
	 */
	public ASNMaster organize(boolean collatedOrganisationMode) throws Exception
	{
		// for( AbstractSyntaxTreeNode iter1 : asnTypeElementList )
		// {
		// System.out.println(iter1.toString("---"));
		// }
		Hashtable<String, Integer> duplicateCheck = checkDuplicate();
		if (duplicateCheck.size() > 0)
		{
			throw new ASNException(ASNErrorCodes.ORGANIZER_DUPLICATE_TYPE_DEFINITION,
						new Object[] { duplicateCheck });
		}
		ASNMaster masterASN = new ASNMaster("Grammar Name");
		boolean flag = false;

		List<AbstractSyntaxTreeNode> markedObject = new ArrayList<AbstractSyntaxTreeNode>();
		for (AbstractSyntaxTreeNode iter1 : asnTypeElementList)
		{
			for (AbstractSyntaxTreeNode iter2 : asnTypeElementList)
			{
				if (!iter2.getName().equals(iter1.getName()))
				{
					if (!iter2.isLeaf())
					{
						for (AbstractSyntaxTreeNode childIter : iter2.getChildren())
						{
							if (childIter.getReference() != null
										&& childIter.getReference().equals(iter1.getName()))
							{
								flag = true;
								if (collatedOrganisationMode)
									collate(childIter, iter1);
								else
									childIter.addChild(iter1);
							}
						}
					}
					else if (iter2.isLeaf() && iter2.getReference() != null
								&& iter2.getReference().equals(iter1.getName()))
					{
						flag = true;
						if (collatedOrganisationMode)
							collate(iter2, iter1);
						else
							iter2.addChild(iter1);
					}
				}
			}
			if (flag)
			{
				markedObject.add(iter1);
				flag = false;
			}
		}

		asnTypeElementList.removeAll(markedObject);
		masterASN.addChildren(asnTypeElementList);

		if (collatedOrganisationMode)
			return revive(masterASN);
		else
			return masterASN;
	}

	/**
	 * Check for the duplicate name from list of NODEs provided for syntax tree
	 * organization
	 * 
	 * @return
	 */
	private Hashtable<String, Integer> checkDuplicate()
	{
		Hashtable<String, Integer> identifierCountMap = new Hashtable<String, Integer>();
		int duplicateCount;
		for (AbstractSyntaxTreeNode mstNodeTop : asnTypeElementList)
		{
			duplicateCount = -1;
			for (AbstractSyntaxTreeNode mstNodeNext : asnTypeElementList)
			{
				if (mstNodeTop.getName().equals(mstNodeNext.getName()))
					duplicateCount++;
			}
			if (duplicateCount > 0)
			{
				identifierCountMap.put(mstNodeTop.getName(), duplicateCount);
			}
		}
		return identifierCountMap;
	}

	/**
	 * This will collate NODEs by eliminating all the intermediate unused node
	 * extracted in grammar reading.
	 * 
	 * @param mstNodeTop
	 * @param mstNodeNext
	 */
	private void collate(AbstractSyntaxTreeNode mstNodeTop,
				AbstractSyntaxTreeNode mstNodeNext)
	{
		if (mstNodeTop.isDefined() && mstNodeNext.isDefined())
		{
			mstNodeTop.setReference(mstNodeNext.getReference());
		}
		else
		{
			mstNodeTop.addChild(mstNodeNext);
		}
	}

	/**
	 * Re-construct the syntax tree to a customized form that can be present to
	 * user.
	 * 
	 * @param msTreeNode
	 * @return
	 */
	private ASNMaster revive(ASNMaster msTreeNode)
	{
		ASNMaster masterASN = null;
		masterASN = msTreeNode.selfCopy();

		for (AbstractSyntaxTreeNode mstNode : msTreeNode.getChildren())
			masterASN.addChild(revive(mstNode));
		return masterASN;
	}

	/**
	 * Supporting method for revive starter and recursion
	 * 
	 * @param mstNode
	 * @return
	 */
	private AbstractSyntaxTreeNode revive(AbstractSyntaxTreeNode mstNode)
	{
		if (mstNode == null || mstNode.isLeaf())
			return new ASNModelViewNode(mstNode.getName(), mstNode.getType());

		if (mstNode.isDefined())
		{
			AbstractSyntaxTreeNode msmNode = mstNode;

			String firstName = mstNode.getName();
			while (msmNode.isDefined())
				msmNode = msmNode.childByIndex(0);

			msmNode.setName(firstName);
			return revive(msmNode);
		}
		else
		{
			AbstractSyntaxTreeNode srNode = new ASNModelViewNode(mstNode.getName(),
						mstNode.getType());

			for (AbstractSyntaxTreeNode mstN : mstNode.getChildren())
				srNode.addChild(revive(mstN));
			return srNode;
		}
	}
}
