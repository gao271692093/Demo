package com.glg.headerintoplist1;

/**
 * Description:
 *
 * @package: com.glg.headerintoplist1
 * @className: ItemTouchStatus
 * @author: gao
 * @date: 2020/8/3 0:02
 */
public interface ItemTouchStatus {

    boolean onItemMove(int fromPosition, int toPosition);

    boolean onItemRemove(int position);

}
