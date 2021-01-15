/*
 * Philippe Carrier 40153985
 * COMP249 Section QQ
 * Assignment # 4
 * Sunday, April 19, 2020
 *
 * Part 2
 * */

import java.util.NoSuchElementException;

/**
 * Class to build a CellList instance that can hold multiple CellNode as LinkedList
 *
 * @author Philippe Carrier
 */
public class CellList {

    private CellNode head;
    private int size;

    /**
     * Class to build CellNode instance. It's the node being linked in the CellList instance.
     */
    class CellNode {
        private CellPhone cellPhone;
        private CellNode pointer;

        /**
         * Default constructor
         */
        public CellNode() {
            this(null, null);
        }

        /**
         * Parametrize constructor
         *
         * @param cellPhone     CellPhone - A cellphone instance
         * @param pointer       CellNode - The following CellNode in the list
         */
        public CellNode(CellPhone cellPhone, CellNode pointer) {
            this.cellPhone = cellPhone;
            this.pointer = pointer;
        }

        /**
         * Copy constructor
         *
         * @param cellNode  CellNode - CellNode to copy
         */
        public CellNode(CellNode cellNode) {
            this(
                    cellNode.cellPhone == null ? null : cellNode.cellPhone.clone(),
                    cellNode.pointer != null ? cellNode.pointer.clone() : null
            );
        }

        /**
         * Deep copy of the CellNode instance
         *
         * @return  CellNode - a CellNode
         */
        public CellNode clone() {
            return new CellNode(this);
        }

        /**
         * Getter for cellPhone variable
         *
         * @return  CellPhone - the cellphone of the cellNode
         */
        public CellPhone getCellPhone() {
            return cellPhone;
        }

        /**
         * Setter for cellPhone variable
         *
         * @param cellPhone CellPhone - The new cellphone of the cellNode
         */
        public void setCellPhone(CellPhone cellPhone) {
            this.cellPhone = cellPhone;
        }

        /**
         * Getter for pointer variable
         *
         * @return  CellNode - the pointer of the cellNode
         */
        public CellNode getPointer() {
            return pointer;
        }

        /**
         * Setter for pointer variable
         *
         * @param pointer   CellNode - The new pointer of the cellNode
         */
        public void setPointer(CellNode pointer) {
            this.pointer = pointer;
        }
    }

    /**
     * Default constructor
     */
    public CellList() {
        head = null;
        size = 0;
    }

    /**
     * Copy constructor
     *
     * @param cellList  CellList - CellList to copy
     */
    public CellList(CellList cellList) {
        head = cellList.head.clone();
        size = cellList.size;
    }

    /**
     * Insert a CellNode at the start (head) of the CellList
     *
     * @param cellPhone     CellPhone - the object of the CellNode
     */
    public void addToStart(CellPhone cellPhone) {
//        This method can result in a privacy leak since the cellphone parameter can be access
//        from the outside (shallow copy), the content of the list could be overwritten from
//        outside the class. It could be fix by making a deep copy of the CellPhone
//        instance.
        size++;
        if (head == null)
            head = new CellNode(cellPhone, null);
        else {
            CellNode current = head;
            head = new CellNode(cellPhone, current);
        }
    }

    /**
     * Check whether the index is positive and smaller than the size (since it's 0 base)
     *
     * @param index     int - The index to verify
     */
    private void validateIndex(int index) {
        try {
            if (index < 0 || index >= size)
                throw new NoSuchElementException();
        } catch (NoSuchElementException e) {
            System.out.println("The inputted index is not in the cell list.");
            System.out.println();
            System.out.println("Closing the program.");
            System.exit(1);
        }
    }

    /**
     * Insert a cellNode at a valid index
     *
     * @param cellPhone CellPhone - the object of the CellNode
     * @param index     int - the index where to add the cellNode
     */
    public void insertAtIndex(CellPhone cellPhone, int index) {
//        This method can result in a privacy leak since the cellphone parameter can be access
//        from the outside (shallow copy), the content of the list could be overwritten from
//        outside the class. It could be fix by making a deep copy of the CellPhone
//        instance.
        validateIndex(index);
        if (index == 0) {
            addToStart(cellPhone);
            return;
        }
        size++;
        int num = 0;
        CellNode current = head;
        while (num != index - 1) {
            current = current.getPointer();
            num++;
        }
        current.setPointer(new CellNode(cellPhone, current.getPointer()));
    }

    /**
     * Delete a cellNode from a valid index
     *
     * @param index     int - the index where to delete the cellNode
     */
    public void deleteFromIndex(int index) {
        validateIndex(index);
        if (index == 0) {
            deleteFromStart();
            return;
        }
        size--;
        int num = 0;
        CellNode current = head;
        while (num != index - 1) {
            current = current.pointer;
            num++;
        }
        current.setPointer(current.getPointer().getPointer());
    }

    /**
     * Delete a cellNode from the start (head) of the cellList
     */
    public void deleteFromStart() {
        if (head == null)
            return;
        size--;
        head = head.getPointer();
    }

    /**
     * Replace the cellNode object at the valid index
     *
     * @param cellPhone     CellPhone - the object of the CellNode
     * @param index         int - the index where to replace the cellNode object
     */
    public void replaceAtIndex(CellPhone cellPhone, int index) {
//        This method can result in a privacy leak since the cellphone parameter can be access
//        from the outside (shallow copy), the content of the list could be overwritten from
//        outside the class. It could be fix by making a deep copy of the CellPhone
//        instance.
        validateIndex(index);
        if (index == 0) {
            head = new CellNode(cellPhone, head.pointer);
            return;
        }
        int num = 0;
        CellNode current = head;
        while (num != index) {
            current = current.getPointer();
            num++;
        }
        current.setCellPhone(cellPhone);
    }

    /**
     * Find the cellNode with the cellPhone object with a specific serial number
     *
     * @param serialNum     long - a cellphone instance serial number
     * @return  CellNode - The cellNode associated with the serial number or null
     */
    public CellNode find(long serialNum) {
//        This method can result in a privacy leak since the cellphone returned by the method allows
//        access from outside of the class to the element of the list. It could be fix by making a
//        deep copy of the returned CellPhone.
        CellNode current = head;
        int iter = 1;
        while (current != null && current.cellPhone.serialNum != serialNum) {
            current = current.getPointer();
            iter++;
        }
        System.out.print("Iteration #" + iter + ": ");
        return current;
    }

    /**
     * Validate that the cellPhone with a specific serial number is in the cellList
     *
     * @param serialNum     long - a cellphone instance serial number
     * @return  boolean - Whether the cellphone is in the cellList or not
     */
    public boolean contains(long serialNum) {
        CellNode current = head;
        while (current != null) {
            if (current.cellPhone.serialNum == serialNum)
                return true;
            current = current.getPointer();
        }
        return false;
    }

    /**
     * Print the content of the cell list with the size of the cell list
     */
    public void showContents() {
        System.out.println("The current size of the list is " + size + ". Here are the contents of the list");
        System.out.println("=====================================================================");
        CellNode current = head;
        int num = 0;
        while(current != null) {
            System.out.print(current.cellPhone);
            System.out.print(" ---> ");
            num++;
            if (num % 3 == 0)
                System.out.println();
            current = current.pointer;
        }
        System.out.println("X");
    }


    /**
     * Compare the content of the list with the content of another one.
     *
     * @param cellList  CellList - the list with which to compare content
     * @return  Whether all the items in the list match the items in the other list
     */
    public boolean equals(CellList cellList) {
        if (this == cellList)
            return true;
        if (size != cellList.size)
            return false;

        CellNode current1 = head;
        while(current1 != null) {
            CellNode current2 = cellList.head;
            while (current2 != null) {
                if(!current1.cellPhone.equals(current2.cellPhone))
                    return false;
                current2 = current2.getPointer();
            }
            current1 = current1.getPointer();
        }
        return true;
    }
}
