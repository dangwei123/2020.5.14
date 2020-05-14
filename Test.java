计算球 的半径和体积
public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int x0=sc.nextInt();
            int y0=sc.nextInt();
            int z0=sc.nextInt();
            int x1=sc.nextInt();
            int y1=sc.nextInt();
            int z1=sc.nextInt();
            double r=Math.sqrt((x1-x0)*(x1-x0)+(y1-y0)*(y1-y0)+(z1-z0)*(z1-z0));
            double v=4.0/3*Math.PI*r*r*r;
            System.out.printf("%.3f %.3f\n",r,v);
        }
    }
}

坏键盘
public class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String ori=sc.next();
            String worn=sc.next();
            Set<Character> set=new HashSet<>();
            for(int i=0;i<worn.length();i++){
                char c=worn.charAt(i);
                if(c>='a'&&c<='z'){
                    c^=32;
                }
                set.add(c);
            }
            Set<Character> res=new HashSet<>();
            for(int i=0;i<ori.length();i++){
                char c=ori.charAt(i);
                if(c>='a'&&c<='z'){
                    c^=32;
                }
                if(!set.contains(c)&&!res.contains(c)){
                    res.add(c);
                    System.out.print(c);
                }
            }
            System.out.println();
        }
    }
}


一种消息接收并打印的数据结构
public class MessageBox {
    private static class Node{
        int val;
        Node next;
        public Node(int val){
            this.val=val;
        }
    }
    private Map<Integer,Node> head=new HashMap<>();
    private Map<Integer,Node> tail=new HashMap<>();
    private int lastPrint;
    public void receive(int num){
        Node node=new Node(num);
        head.put(num,node);
        tail.put(num,node);
        if(head.containsKey(num+1)){
            node.next=head.get(num+1);
            head.remove(num+1);
            tail.remove(num);
        }
        if(tail.containsKey(num-1)){
            tail.get(num-1).next=node;
            tail.remove(num-1);
            head.remove(num);
        }
        if(head.containsKey(lastPrint+1)){
            print();
        }
    }

    private void print(){
        Node cur=head.remove(lastPrint+1);
        while(cur!=null){
            System.out.println(cur.val);
            cur=cur.next;
            lastPrint++;
        }
        tail.remove(lastPrint);
    }
}


数据流的中位数
public class MedianFinder {
    PriorityQueue<Integer> max=new PriorityQueue<>((a, b)->b-a);
    PriorityQueue<Integer> min=new PriorityQueue<>();
    int size;
    /** initialize your data structure here. */
    public MedianFinder() {

    }

    public void addNum(int num) {
        max.offer(num);
        min.offer(max.poll());
        size++;
        if((size&1)==1){
            max.offer(min.poll());
        }
    }

    public double findMedian() {
        if((size&1)==1) return max.peek();
        return (max.peek()+min.peek())/2.0;
    }
}


寻找两个有序数组的中位数
public class FindMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m=nums1.length;
        int n=nums2.length;
        if(m>n){
            int[] tmp=nums1;
            nums1=nums2;
            nums2=tmp;

            int t=m;
            m=n;
            n=t;
        }
        int half=(m+n+1)/2;
        int left=0;
        int right=m;
        while(left<=right){
            int i=left+(right-left)/2;
            int j=half-i;
            if(i>0&&nums1[i-1]>nums2[j]){
                right=i-1;
            }else if(i<m&&nums1[i]<nums2[j-1]){
                left=i+1;
            }else{
                int maxleft=0;
                if(i==0){
                    maxleft=nums2[j-1];
                }else if(j==0){
                    maxleft=nums1[i-1];
                }else{
                    maxleft=Math.max(nums1[i-1],nums2[j-1]);
                }
                if(((m+n)&1)==1){
                    return maxleft;
                }

                int minright=0;
                if(i==m){
                    minright=nums2[j];
                }else if(j==n){
                    minright=nums1[i];
                }else{
                    minright=Math.min(nums1[i],nums2[j]);
                }
                return (minright+maxleft)/2.0;
            }
        }
        return 0.0;
    }
}
