---
title: API
order: 5
category:
- Guide
description: Mapstruct MapStructPlus Converter Converter接口API converter api 
---

## Converter

The previous sections, which were all about configuring transformation logic on entity classes, were mainly applied at the compile stage. In practice, the framework provides the `Converter` class to perform the concreate transformation logic.

This class provides the following methods for type conversion:

- **`<S, T> T convert(S source, Class<T> targetType)`**

Passes in the object and the target type to be converted, and finally returns an intance object of the target type.

- **`<S, T> T convert(S source, T target)`**

Passes in the `source` object and `target` object, finally returns target object, and eventually converting the properties in the `source` object to the `target` object. 

This method differs from the above in that it returns an incoming `taring` object, whereas the above method return a new object.

- **`<S, T> List<T> convert(List<S> source, Class<T> targetType)`**

This method converts a collection of source types(`source`) to a collection of target types(`targetType`)

- **`<T> T convert(Map<String, Object> map, Class<T> target)`**

This method converts a `Map<String, Object>` to an instance object of the target type.