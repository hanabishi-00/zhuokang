package main.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
/**
 * 调速系统故障树诊断主模块，通过该类进入故障树诊断
 *
 *
 */
public class FaultTreeMain {
    private static DefaultMutableTreeNode root;
    private static DefaultTreeModel model;
    private static MakeFaultTree makeFaultTree;     //建树
    private static CaculateMinCutset caculateMinCutset;     //计算最小割集
}
