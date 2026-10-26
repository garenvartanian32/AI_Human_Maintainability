class Solution {
    public ListNode doubleIt(ListNode head) {
        ListNode temp = head;
        ListNode prev = null;
        while(temp!=null){
            int val = temp.val;
            temp.val = val*2;
            if(temp.val>9){
                temp.val = temp.val%10;
                if(prev!=null){
                    prev.val+=1;
                }
            }
            prev = temp;
            temp = temp.next;
        }
        return head;
    }
}