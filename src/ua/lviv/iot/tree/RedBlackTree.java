package ua.lviv.iot.tree;

public class RedBlackTree {

    public Node root;
    public Node leaf;

    public RedBlackTree() {
        leaf = new Node();
        leaf.color = Color.BLACK;
        leaf.left = null;
        leaf.right = null;
        root = leaf;
    }

    public void deleteBalance(Node node) {
        Node s;
        while (node != root && node.color == Color.BLACK) {
            if (node == node.parent.left) {
                s = node.parent.right;
                if (s.color == Color.RED) {
                    s.color = Color.BLACK;
                    node.parent.color = Color.RED;
                    leftRotate(node.parent);
                    s = node.parent.right;
                }

                if (s.left.color == Color.BLACK && s.right.color == Color.BLACK) {
                    s.color = Color.RED;
                    node = node.parent;
                } else {
                    if (s.right.color == Color.BLACK) {
                        s.left.color = Color.BLACK;
                        s.color = Color.RED;
                        rightRotate(s);
                        s = node.parent.right;
                    }

                    s.color = node.parent.color;
                    node.parent.color = Color.BLACK;
                    s.right.color = Color.BLACK;
                    leftRotate(node.parent);
                    node = root;
                }
            } else {
                s = node.parent.left;
                if (s.color == Color.RED) {
                    s.color = Color.BLACK;
                    node.parent.color = Color.RED;
                    rightRotate(node.parent);
                    s = node.parent.left;
                }

                if (s.right.color == Color.BLACK && s.right.color == Color.BLACK) {
                    s.color = Color.RED;
                    node = node.parent;
                } else {
                    if (s.left.color == Color.BLACK) {
                        s.right.color = Color.BLACK;
                        s.color = Color.RED;
                        leftRotate(s);
                        s = node.parent.left;
                    }

                    s.color = node.parent.color;
                    node.parent.color = Color.BLACK;
                    s.left.color = Color.BLACK;
                    rightRotate(node.parent);
                    node = root;
                }
            }
        }
        node.color = Color.BLACK;
    }

    private void transplantNodes(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    public void deleteNode(Node node, int key) {
        Node z = leaf;
        Node x, y;
        while (node != leaf) {
            if (node.data == key) {
                z = node;
            }

            if (node.data <= key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (z == leaf) {
            System.out.println("Such value (" + key + ") was not found!");
            return;
        }

        y = z;
        Color yOriginalColor = y.color;
        if (z.left == leaf) {
            x = z.right;
            transplantNodes(z, z.right);
        } else if (z.right == leaf) {
            x = z.left;
            transplantNodes(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                transplantNodes(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            transplantNodes(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == Color.BLACK) {
            deleteBalance(x);
        }
    }

    public void printTree(Node root) {
        if (root != leaf) {
            printTree(root.left);
            System.out.print(root.data + " ");
            printTree(root.right);
        }
    }

    public Node minimum(Node node) {
        while (node.left != leaf) {
            node = node.left;
        }
        return node;
    }

    public Node maximum(Node node) {
        while (node.right != leaf) {
            node = node.right;
        }
        return node;
    }

    public void leftRotate(Node node) {
        Node y = node.right;
        node.right = y.left;
        if (y.left != leaf) {
            y.left.parent = node;
        }
        y.parent = node.parent;
        if (node.parent == null) {
            this.root = y;
        } else if (node == node.parent.left) {
            node.parent.left = y;
        } else {
            node.parent.right = y;
        }
        y.left = node;
        node.parent = y;
    }

    public void rightRotate(Node node) {
        Node y = node.left;
        node.left = y.right;
        if (y.right != leaf) {
            y.right.parent = node;
        }
        y.parent = node.parent;
        if (node.parent == null) {
            this.root = y;
        } else if (node == node.parent.right) {
            node.parent.right = y;
        } else {
            node.parent.left = y;
        }
        y.right = node;
        node.parent = y;
    }

    public void insert(int key, Color color) {
        Node node = new Node();
        node.parent = null;
        node.data = key;
        node.left = leaf;
        node.right = leaf;
        node.color = color;

        Node y = null;
        Node x = this.root;

        while (x != leaf) {
            y = x;
            if (node.data < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.data < y.data) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.color = Color.BLACK;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

    }

}
