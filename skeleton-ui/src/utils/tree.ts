export interface TreeNode { id: number; parentId: number; children?: TreeNode[] }
export function listToTree<T extends TreeNode>(list: T[], parentId = 0): T[] {
  return list
    .filter(item => item.parentId === parentId)
    .map(item => ({ ...item, children: listToTree(list, item.id) }))
}
