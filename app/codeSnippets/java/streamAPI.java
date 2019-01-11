// 将集合放到流水线上，并使用并行流
Stream<T> 集合流 = 集合.stream().parallel();

// 描述如何处理集合元素
Predicate<? super T> 筛选规则 = (T t) -> return true;
Comparator<? super T> 比较规则 = (T t1, T t2) -> return t1 - t2;
Function<? super T, ? extends R> 映射规则 = (T t) -> return new R(t.*);

// 在流水线上处理集合元素：筛选、排序、分页、映射
Stream<R> 结果流 = 集合流
	.filter(筛选规则)
	.sorted(比较规则)
	.skip(n-1).limit(页面大小)
	.map(映射规则)
	.distinct();

// 数据收集：如何进行两两运算
BinaryOperator<T> 收集规则 = (T t1, T t2) -> return t1;
集合流.reduce(初始值, 收集规则);

// 从流水线上下来（转为集合：toList/toSet/toMap）
List<R> 结果集 =	结果流.collect(Collectors.toList());