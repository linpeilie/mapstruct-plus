import{_ as n,o as s,c as a,e as t}from"./app-S9PLs8Z3.js";const p={},e=t(`<blockquote><p>当前特性从 1.2.2 开始支持</p></blockquote><p>当需要进行枚举转换时（例如枚举转换为编码值，或者由编码转换为枚举），可以在目标枚举添加 <code>@AutoEnumMapper</code> 注解， 增加该注解后，在任意类型中需要转换该枚举时都可以自动转换。</p><p>使用该注解需要注意：<strong>当前枚举必须有一个可以保证唯一的字段</strong>，并在使用当前注解时，将该字段名，添加到注解提供的 <code>value</code> 属性中。</p><p>还有就是<strong>枚举和使用枚举的类，要在同一个模块中</strong>。</p><p>例如：</p><ul><li>商品状态枚举（<code>Goods</code>）</li></ul><div class="language-java line-numbers-mode" data-ext="java"><pre class="language-java"><code><span class="token annotation punctuation">@Getter</span>
<span class="token annotation punctuation">@AllArgsConstructor</span>
<span class="token annotation punctuation">@AutoEnumMapper</span><span class="token punctuation">(</span><span class="token string">&quot;state&quot;</span><span class="token punctuation">)</span>
<span class="token keyword">public</span> <span class="token keyword">enum</span> <span class="token class-name">GoodsStateEnum</span> <span class="token punctuation">{</span>
    <span class="token function">ENABLED</span><span class="token punctuation">(</span><span class="token number">1</span><span class="token punctuation">,</span> <span class="token string">&quot;启用&quot;</span><span class="token punctuation">)</span><span class="token punctuation">,</span>
    <span class="token function">DISABLED</span><span class="token punctuation">(</span><span class="token number">0</span><span class="token punctuation">,</span> <span class="token string">&quot;禁用&quot;</span><span class="token punctuation">)</span><span class="token punctuation">;</span>

    <span class="token keyword">private</span> <span class="token keyword">final</span> <span class="token class-name">Integer</span> state<span class="token punctuation">;</span>
    <span class="token keyword">private</span> <span class="token keyword">final</span> <span class="token class-name">String</span> desc<span class="token punctuation">;</span>

<span class="token punctuation">}</span>
</code></pre><div class="line-numbers" aria-hidden="true"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><p>在当前枚举中添加注解 <code>@AutoEnumMapper</code>，且指定唯一字段为 <code>state</code>。</p><ul><li>商品类（<code>Goods</code>）</li></ul><div class="language-java line-numbers-mode" data-ext="java"><pre class="language-java"><code><span class="token annotation punctuation">@Data</span>
<span class="token annotation punctuation">@AutoMapper</span><span class="token punctuation">(</span>target <span class="token operator">=</span> <span class="token class-name">GoodsVo</span><span class="token punctuation">.</span><span class="token keyword">class</span><span class="token punctuation">,</span> reverseConvertGenerate <span class="token operator">=</span> <span class="token boolean">false</span><span class="token punctuation">)</span>
<span class="token keyword">public</span> <span class="token keyword">class</span> <span class="token class-name">Goods</span> <span class="token punctuation">{</span>

    <span class="token keyword">private</span> <span class="token class-name">GoodsStateEnum</span> state<span class="token punctuation">;</span>

<span class="token punctuation">}</span>
</code></pre><div class="line-numbers" aria-hidden="true"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><ul><li>商品VO对象（<code>GoodsVo</code>）</li></ul><div class="language-java line-numbers-mode" data-ext="java"><pre class="language-java"><code><span class="token annotation punctuation">@Data</span>
<span class="token keyword">public</span> <span class="token keyword">class</span> <span class="token class-name">GoodsVo</span> <span class="token punctuation">{</span>

    <span class="token keyword">private</span> <span class="token class-name">Integer</span> state<span class="token punctuation">;</span>

<span class="token punctuation">}</span>
</code></pre><div class="line-numbers" aria-hidden="true"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><ul><li>测试类</li></ul><div class="language-java line-numbers-mode" data-ext="java"><pre class="language-java"><code><span class="token annotation punctuation">@Test</span>
<span class="token keyword">public</span> <span class="token keyword">void</span> <span class="token function">enumMapTest</span><span class="token punctuation">(</span><span class="token punctuation">)</span> <span class="token punctuation">{</span>
    <span class="token keyword">final</span> <span class="token class-name">GoodsVo</span> goodsVo <span class="token operator">=</span> converter<span class="token punctuation">.</span><span class="token function">convert</span><span class="token punctuation">(</span>goods<span class="token punctuation">,</span> <span class="token class-name">GoodsVo</span><span class="token punctuation">.</span><span class="token keyword">class</span><span class="token punctuation">)</span><span class="token punctuation">;</span>
    <span class="token class-name">System</span><span class="token punctuation">.</span>out<span class="token punctuation">.</span><span class="token function">println</span><span class="token punctuation">(</span>goodsVo<span class="token punctuation">)</span><span class="token punctuation">;</span>
    <span class="token class-name">Assert</span><span class="token punctuation">.</span><span class="token function">equals</span><span class="token punctuation">(</span>goodsVo<span class="token punctuation">.</span><span class="token function">getState</span><span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token punctuation">,</span> goods<span class="token punctuation">.</span><span class="token function">getState</span><span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token punctuation">.</span><span class="token function">getState</span><span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token punctuation">)</span><span class="token punctuation">;</span>

    <span class="token keyword">final</span> <span class="token class-name">Goods</span> goods2 <span class="token operator">=</span> converter<span class="token punctuation">.</span><span class="token function">convert</span><span class="token punctuation">(</span>goodsVo<span class="token punctuation">,</span> <span class="token class-name">Goods</span><span class="token punctuation">.</span><span class="token keyword">class</span><span class="token punctuation">)</span><span class="token punctuation">;</span>
    <span class="token class-name">System</span><span class="token punctuation">.</span>out<span class="token punctuation">.</span><span class="token function">println</span><span class="token punctuation">(</span>goods2<span class="token punctuation">)</span><span class="token punctuation">;</span>
    <span class="token class-name">Assert</span><span class="token punctuation">.</span><span class="token function">equals</span><span class="token punctuation">(</span>goods2<span class="token punctuation">.</span><span class="token function">getState</span><span class="token punctuation">(</span><span class="token punctuation">)</span><span class="token punctuation">,</span> <span class="token class-name">GoodsStateEnum</span><span class="token punctuation">.</span><span class="token constant">ENABLED</span><span class="token punctuation">)</span><span class="token punctuation">;</span>
<span class="token punctuation">}</span>
</code></pre><div class="line-numbers" aria-hidden="true"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div>`,14),o=[e];function c(l,u){return s(),a("div",null,o)}const d=n(p,[["render",c],["__file","enum-convert.html.vue"]]);export{d as default};
