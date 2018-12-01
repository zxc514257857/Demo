package android.support.v7.widget.helper;

/**
 * 在ItemTouchHelper构造中把Callback穿进去，它保存的时候一个package级别的成员变量，
 * 所以在android.support.v7.widget.helper包下新建了一个YolandaItemTouchHelper类
 */
public class YolandaItemTouchHelper extends ItemTouchHelper {

    public YolandaItemTouchHelper(Callback callback) {
        super(callback);
    }

    public Callback getCallback(){
        return mCallback;
    }
}
