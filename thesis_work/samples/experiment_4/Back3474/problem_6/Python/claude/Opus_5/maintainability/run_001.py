"""Maximum elegance of a k-item selection (total profit + distinct categories squared)."""


class Solution:
    def findMaximumElegance(self, items: list[list[int]], k: int) -> int:
        """Return the best elegance over all subsets of exactly k items.

        Start with the k most profitable items. Then repeatedly trade the least
        profitable *duplicate-category* item for the most profitable item of a
        brand-new category: profit can only fall, but the distinct-category term
        grows quadratically, so the trade is sometimes worth it.
        """
        by_profit_descending = sorted(items, key=lambda item: item[0], reverse=True)

        total_profit = 0
        seen_categories: set[int] = set()
        # Profits of chosen items whose category was already represented,
        # kept smallest-last so pop() removes the cheapest to give up.
        droppable_profits: list[int] = []

        for profit, category in by_profit_descending[:k]:
            total_profit += profit
            if category in seen_categories:
                droppable_profits.append(profit)
            else:
                seen_categories.add(category)

        best_elegance = total_profit + len(seen_categories) ** 2

        for profit, category in by_profit_descending[k:]:
            if not droppable_profits:
                break
            if category in seen_categories:
                continue

            total_profit += profit - droppable_profits.pop()
            seen_categories.add(category)
            best_elegance = max(best_elegance, total_profit + len(seen_categories) ** 2)

        return best_elegance