public class BST<E extends Comparable<E>> {
    //创建二叉树
     private class Node{
         //创建属性
        public E e;
        public Node left,right;
        //初始化节点
        public Node(E e) {
            this.e = e;
            left=null;
            right=null;
        }
    }

    //二叉树的属性
    //二叉树所具备的节点
    private Node root;
     //二叉树的大小
    private int size;
     //初始化二叉树
    public BST() {
         root=null;
        size=0;
    }
    //判断二叉树是否为空
    public boolean isEmpty(){
        return size==0;
    }
    //返回二叉树的大小
    public int getSize(){
        return size;
    }
    //向二叉树中插入元素
    public void add(E e){
        root=add(root,e);
    }
   /* private void add(Node node,E e){
       //如果插入的元素等于该节点的元素，则直接返回
        if (e.equals(node.e)){
            return;
        }
        else if(e.compareTo((E) node.e)<0&&node.left==null){
            node.left=new Node(e);
            size++;
            return;
        }
        else if(e.compareTo(node.e)>0&&node.right==null){
            node.right=new Node(e);
            size++;
            return;
        }
        //如果需要插入的元素的比节点的值小，并且该节点的左节点不为null，继续调用插入
        else if (e.compareTo(node.e)<0){
            add(node.left,e);
        }
        else {
            add(node.right,e);
        }
    }*/
   public Node add(Node node,E e){
       //如果节点为空，则直接创建节点
       if (node==null){
           size++;
           return new Node(e);
       }
       //若果插入的值小于根节点的值，则往父节点的左分支插入
      if (e.compareTo(node.e)<0){

           node.left=add(node.left,e);
       }
       //如果插入的值大于根节点的值，则往父节点的有分支插入
       else if (e.compareTo(node.e)>0){
           node.right=add(node.right,e);
       }
       return node;
   }

   //查找二叉树中是否存在指定的元素
    public boolean contains(E e){
       return contains(root,e);
    }
    private boolean contains(Node node,E e){
       if (node==null){
           return false;
       }
       if (e.compareTo(node.e)==0){
           return true;
       }
       else if (e.compareTo(node.e)<0){
           return  contains(node.left,e);
       }
       else {
           return contains(node.right,e);
       }
    }
    //二叉树的前序遍历
    public void preOreder(){
       preOrder(root);
    }
    private void preOrder(Node root){
       //如果根节点为空，则直接返回
        if (root==null){
            return;
        }
        System.out.println(root.e);
        //递归遍历，二叉树中的每一个节点
        preOrder(root.left);
        preOrder(root.right);
    }
    //二叉树的中序遍历,该遍历是有序的
    public void inOrder(){
       inOrder(root);
    }
    private void inOrder(Node node){
       if (node==null){
           return;
       }
       inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }
    //二叉树的后序遍历
    public void afterOrder(){
       afterOrder(root);
    }
    private void afterOrder(Node node){
       if (node==null){
           return;
       }
       afterOrder(node.left);
       afterOrder(node.right);
        System.out.println(node.e);
    }
    //二叉树前序遍历，非递归
    public  void preOrderNR(){
       Stack<Node> stack=new Stack<>();
       stack.push(root);
       while (!stack.isEmpty()){
           Node cur = stack.pop();
           System.out.println(cur.e);
           if (cur.right!=null){
               stack.push(cur.right);
           }
           if (cur.left!=null){
               stack.push(cur.left);
           }
       }
    }
    //二叉树的层级遍历
    public void levelOrder(){
        Queue<Node> queue=new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node cur=queue.remove();
            System.out.println(cur.e);
           if (cur.left!=null){
               queue.add(cur.left);
           }
           if (cur.right!=null){
               queue.add(cur.right);
           }
        }
    }



    //寻找二叉树中的最小值
    public E minmum(){
       if (size==0){
           throw new  IllegalArgumentException("the tree is null");
       }
       return min(root).e;
    }
    private Node min(Node node){
       if (node.left==null){
           return node;
       }
      return  min(node.left);
    }

    //寻找二叉树中的最大数
    public E maxmum(){
       if (size==0){
           throw new  IllegalArgumentException("the tree is null");
       }
      return  max(root).e;
    }
    private Node max(Node root){
       if (root.right==null){
           return root;
       }
       return max(root.right);
    }
    //删除二叉树中最小的元素
    public  E removeMin(){
       E e=minmum();
      root=removeMinNode(root);
       return e;
    }
    //删除最小节点，并且返回根节点
    private Node removeMinNode(Node node){
       if (node.left==null){
           Node delete=node.right;
           node.right=null;
           size--;
           return delete;
       }
       node.left=removeMinNode(node.left);
       return node;
    }
    //删除最大的元素
    public  E removeMax(){

       E e=maxmum();
       removemax(root);
       return e;
    }
    private Node removemax(Node node){
       if (node.right==null){
           Node cur=node.left;
           node.left=null;
           size--;
           return cur;
       }
       node.right=removemax(node.right);
       return node;
    }



    //删除二叉树中的任意元素

    public void delete(E e){

    }
    private Node delete(Node node,E e){
       //如果node根节点找不到需要删除的元素，返回null
        if (node==null){
            return null;
        }
        //将需要删除的节点的值大于删除的值，则继续往节点的左节点走
        if (e.compareTo(node.e)<0){
            node.left=delete(node.left,e);
            return node;
        }
        //将需要删除的节点的值小于删除的值，则继续往节点的右节点走
        if (e.compareTo(node.e)>0){
            node.right=delete(node.right,e);
            return node;
        }
        //如果节点值等于需要删除的节点值
        if (e.compareTo(node.e)==0){
            //如果节点的左节点为空，则直接删除该节点
            if (node.left==null){
                Node reRight=node.right;
                node.right=null;
                size--;
                return reRight;
            }
            //如果节点的右节点为空，则直接删除
            else if (node.right==null){
                Node reLift=node.left;
                node.left=null;
                size--;
                return reLift;
            }
            //如果该节点的左右节点都不为空
            else{
                //找出删除节点与之最近的节点,最为新的节点
                Node newNode=min(node.right);
                //removeMinNode(node.right),返回node.right根节点，
                //将newNode的右节点指向node.right
                newNode.right=removeMinNode(node.right);
                newNode.left=node.left;
                node.left=node.right=null;
                return  newNode;
            }
        }
        return  node;
    }
}
