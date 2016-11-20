/**
 * 
 */
package com.ec.analytics.checks.generics;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ec.containers.pojo.EndecaItem;
import com.ec.containers.pojo.GenericItem;

public class groupItemGenerics {

	public static Map<String, Integer> byFamilyCount(List<EndecaItem> items) {
		Map<String, Integer> groupedByFamilyCount = items.stream().collect(
				Collectors.groupingBy(EndecaItem::getFamilyGroup, Collectors.summingInt(EndecaItem::addOne)));
		return groupedByFamilyCount;
	}

	public static Map<String, List<EndecaItem>> groupByFamilyEndecaList(List<EndecaItem> items) {
		Map<String, List<EndecaItem>> groupedByFamily = items.stream()
				.collect(Collectors.groupingBy(GenericItem::getFamilyGroup));
		return groupedByFamily;
	}

	public static Map<String, List<EndecaItem>> groupByRelevancyOrder(List<EndecaItem> items) {
		Map<String, List<EndecaItem>> groupedByRelevancy = items.stream()
				.collect(Collectors.groupingBy(EndecaItem::getRelevancyOrder));
		return groupedByRelevancy;
	}
}