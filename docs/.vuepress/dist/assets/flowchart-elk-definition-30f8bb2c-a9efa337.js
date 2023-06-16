import{_ as W}from"./app-fb42e106.js";import{o as X,c as F,d as j}from"./add-html-label-8be7803c-770e778e.js";import{M as y,X as B,$ as O,e as K,I as N,u as V,K as A,T as D,d as L}from"./mermaid.esm.min-7b9a39b0.js";import{K as q,G as Y,e as J}from"./edges-69864166-108f2377.js";import{z as Q}from"./isPlainObject-0b348591-447b345c.js";import"./framework-6ad2459b.js";import"./svgDraw-1c03c25e-a3d84b23.js";import"./array-2ff2c7a6-ffeda358.js";import"./constant-2fe7eae5-45b4460e.js";const Z=(e,r,t)=>{const{parentById:a}=t,i=new Set;let n=e;for(;n;){if(i.add(n),n===r)return n;n=a[n]}for(n=r;n;){if(i.has(n))return n;n=a[n]}return"root"};let P;const f={},ee={};let v={};const te=function(e,r,t,a,i,n,p){const l=t.select(`[id="${r}"]`),w=l.insert("g").attr("class","nodes");return Object.keys(e).forEach(function(d){const s=e[d];let _="default";s.classes.length>0&&(_=s.classes.join(" "));const g=N(s.styles);let k=s.text!==void 0?s.text:s.id,o;const h={width:0,height:0};if(V(O().flowchart.htmlLabels)){const S={label:k.replace(/fa[blrs]?:fa-[\w-]+/g,I=>`<i class='${I.replace(":"," ")}'></i>`)};o=j(l,S).node();const C=o.getBBox();h.width=C.width,h.height=C.height,h.labelNode=o,o.parentNode.removeChild(o)}else{const S=a.createElementNS("http://www.w3.org/2000/svg","text");S.setAttribute("style",g.labelStyle.replace("color:","fill:"));const C=k.split(A.lineBreakRegex);for(const U of C){const T=a.createElementNS("http://www.w3.org/2000/svg","tspan");T.setAttributeNS("http://www.w3.org/XML/1998/namespace","xml:space","preserve"),T.setAttribute("dy","1em"),T.setAttribute("x","1"),T.textContent=U,S.appendChild(T)}o=S;const I=o.getBBox();h.width=I.width,h.height=I.height,h.labelNode=o}const $=[{id:s.id+"-west",layoutOptions:{"port.side":"WEST"}},{id:s.id+"-east",layoutOptions:{"port.side":"EAST"}},{id:s.id+"-south",layoutOptions:{"port.side":"SOUTH"}},{id:s.id+"-north",layoutOptions:{"port.side":"NORTH"}}];let m=0,c="",E={};switch(s.type){case"round":m=5,c="rect";break;case"square":c="rect";break;case"diamond":c="question",E={portConstraints:"FIXED_SIDE"};break;case"hexagon":c="hexagon";break;case"odd":c="rect_left_inv_arrow";break;case"lean_right":c="lean_right";break;case"lean_left":c="lean_left";break;case"trapezoid":c="trapezoid";break;case"inv_trapezoid":c="inv_trapezoid";break;case"odd_right":c="rect_left_inv_arrow";break;case"circle":c="circle";break;case"ellipse":c="ellipse";break;case"stadium":c="stadium";break;case"subroutine":c="subroutine";break;case"cylinder":c="cylinder";break;case"group":c="rect";break;case"doublecircle":c="doublecircle";break;default:c="rect"}const x={labelStyle:g.labelStyle,shape:c,labelText:k,rx:m,ry:m,class:_,style:g.style,id:s.id,link:s.link,linkTarget:s.linkTarget,tooltip:i.db.getTooltip(s.id)||"",domId:i.db.lookUpDomId(s.id),haveCallback:s.haveCallback,width:s.type==="group"?500:void 0,dir:s.dir,type:s.type,props:s.props,padding:O().flowchart.padding};let u,b;x.type!=="group"&&(b=Y(w,x,s.dir),u=b.node().getBBox());const M={id:s.id,ports:s.type==="diamond"?$:[],layoutOptions:E,labelText:k,labelData:h,domId:i.db.lookUpDomId(s.id),width:u==null?void 0:u.width,height:u==null?void 0:u.height,type:s.type,el:b,parent:n.parentById[s.id]};v[x.id]=M}),p},R=(e,r,t)=>{const a={TB:{in:{north:"north"},out:{south:"west",west:"east",east:"south"}},LR:{in:{west:"west"},out:{east:"south",south:"north",north:"east"}},RL:{in:{east:"east"},out:{west:"north",north:"south",south:"west"}},BT:{in:{south:"south"},out:{north:"east",east:"west",west:"north"}}};return a.TD=a.TB,y.info("abc88",t,r,e),a[t][r][e]},z=(e,r,t)=>{if(y.info("getNextPort abc88",{node:e,edgeDirection:r,graphDirection:t}),!f[e])switch(t){case"TB":case"TD":f[e]={inPosition:"north",outPosition:"south"};break;case"BT":f[e]={inPosition:"south",outPosition:"north"};break;case"RL":f[e]={inPosition:"east",outPosition:"west"};break;case"LR":f[e]={inPosition:"west",outPosition:"east"};break}const a=r==="in"?f[e].inPosition:f[e].outPosition;return r==="in"?f[e].inPosition=R(f[e].inPosition,r,t):f[e].outPosition=R(f[e].outPosition,r,t),a},re=(e,r)=>{let t=e.start,a=e.end;const i=v[t],n=v[a];return!i||!n?{source:t,target:a}:(i.type==="diamond"&&(t=`${t}-${z(t,"out",r)}`),n.type==="diamond"&&(a=`${a}-${z(a,"in",r)}`),{source:t,target:a})},oe=function(e,r,t,a){y.info("abc78 edges = ",e);const i=a.insert("g").attr("class","edgeLabels");let n={},p=r.db.getDirection(),l,w;if(e.defaultStyle!==void 0){const d=N(e.defaultStyle);l=d.style,w=d.labelStyle}return e.forEach(function(d){var s="L-"+d.start+"-"+d.end;n[s]===void 0?(n[s]=0,y.info("abc78 new entry",s,n[s])):(n[s]++,y.info("abc78 new entry",s,n[s]));let _=s+"-"+n[s];y.info("abc78 new link id to be used is",s,_,n[s]);var g="LS-"+d.start,k="LE-"+d.end;const o={style:"",labelStyle:""};switch(o.minlen=d.length||1,d.type==="arrow_open"?o.arrowhead="none":o.arrowhead="normal",o.arrowTypeStart="arrow_open",o.arrowTypeEnd="arrow_open",d.type){case"double_arrow_cross":o.arrowTypeStart="arrow_cross";case"arrow_cross":o.arrowTypeEnd="arrow_cross";break;case"double_arrow_point":o.arrowTypeStart="arrow_point";case"arrow_point":o.arrowTypeEnd="arrow_point";break;case"double_arrow_circle":o.arrowTypeStart="arrow_circle";case"arrow_circle":o.arrowTypeEnd="arrow_circle";break}let h="",$="";switch(d.stroke){case"normal":h="fill:none;",l!==void 0&&(h=l),w!==void 0&&($=w),o.thickness="normal",o.pattern="solid";break;case"dotted":o.thickness="normal",o.pattern="dotted",o.style="fill:none;stroke-width:2px;stroke-dasharray:3;";break;case"thick":o.thickness="thick",o.pattern="solid",o.style="stroke-width: 3.5px;fill:none;";break}if(d.style!==void 0){const x=N(d.style);h=x.style,$=x.labelStyle}o.style=o.style+=h,o.labelStyle=o.labelStyle+=$,d.interpolate!==void 0?o.curve=D(d.interpolate,L):e.defaultInterpolate!==void 0?o.curve=D(e.defaultInterpolate,L):o.curve=D(ee.curve,L),d.text===void 0?d.style!==void 0&&(o.arrowheadStyle="fill: #333"):(o.arrowheadStyle="fill: #333",o.labelpos="c"),o.labelType="text",o.label=d.text.replace(A.lineBreakRegex,`
`),d.style===void 0&&(o.style=o.style||"stroke: #333; stroke-width: 1.5px;fill:none;"),o.labelStyle=o.labelStyle.replace("color:","fill:"),o.id=_,o.classes="flowchart-link "+g+" "+k;const m=J(i,o),{source:c,target:E}=re(d,p);y.debug("abc78 source and target",c,E),t.edges.push({id:"e"+d.start+d.end,sources:[c],targets:[E],labelEl:m,labels:[{width:o.width,height:o.height,orgWidth:o.width,orgHeight:o.height,text:o.label,layoutOptions:{"edgeLabels.inline":"true","edgeLabels.placement":"CENTER"}}],edgeData:o})}),t},ae=function(e,r,t,a){let i="";switch(a&&(i=window.location.protocol+"//"+window.location.host+window.location.pathname+window.location.search,i=i.replace(/\(/g,"\\("),i=i.replace(/\)/g,"\\)")),r.arrowTypeStart){case"arrow_cross":e.attr("marker-start","url("+i+"#"+t+"-crossStart)");break;case"arrow_point":e.attr("marker-start","url("+i+"#"+t+"-pointStart)");break;case"arrow_barb":e.attr("marker-start","url("+i+"#"+t+"-barbStart)");break;case"arrow_circle":e.attr("marker-start","url("+i+"#"+t+"-circleStart)");break;case"aggregation":e.attr("marker-start","url("+i+"#"+t+"-aggregationStart)");break;case"extension":e.attr("marker-start","url("+i+"#"+t+"-extensionStart)");break;case"composition":e.attr("marker-start","url("+i+"#"+t+"-compositionStart)");break;case"dependency":e.attr("marker-start","url("+i+"#"+t+"-dependencyStart)");break;case"lollipop":e.attr("marker-start","url("+i+"#"+t+"-lollipopStart)");break}switch(r.arrowTypeEnd){case"arrow_cross":e.attr("marker-end","url("+i+"#"+t+"-crossEnd)");break;case"arrow_point":e.attr("marker-end","url("+i+"#"+t+"-pointEnd)");break;case"arrow_barb":e.attr("marker-end","url("+i+"#"+t+"-barbEnd)");break;case"arrow_circle":e.attr("marker-end","url("+i+"#"+t+"-circleEnd)");break;case"aggregation":e.attr("marker-end","url("+i+"#"+t+"-aggregationEnd)");break;case"extension":e.attr("marker-end","url("+i+"#"+t+"-extensionEnd)");break;case"composition":e.attr("marker-end","url("+i+"#"+t+"-compositionEnd)");break;case"dependency":e.attr("marker-end","url("+i+"#"+t+"-dependencyEnd)");break;case"lollipop":e.attr("marker-end","url("+i+"#"+t+"-lollipopEnd)");break}},ie=function(e,r){y.info("Extracting classes"),r.db.clear("ver-2");try{return r.parse(e),r.db.getClasses()}catch{return{}}},le=function(e){const r={parentById:{},childrenById:{}},t=e.getSubGraphs();return y.info("Subgraphs - ",t),t.forEach(function(a){a.nodes.forEach(function(i){r.parentById[i]=a.id,r.childrenById[a.id]===void 0&&(r.childrenById[a.id]=[]),r.childrenById[a.id].push(i)})}),t.forEach(function(a){a.id,r.parentById[a.id]!==void 0&&r.parentById[a.id]}),r},se=function(e,r,t){const a=Z(e,r,t);if(a===void 0||a==="root")return{x:0,y:0};const i=v[a].offset;return{x:i.posX,y:i.posY}},ne=function(e,r,t,a,i){const n=se(r.sources[0],r.targets[0],i),p=r.sections[0].startPoint,l=r.sections[0].endPoint,w=(r.sections[0].bendPoints?r.sections[0].bendPoints:[]).map(h=>[h.x+n.x,h.y+n.y]),d=[[p.x+n.x,p.y+n.y],...w,[l.x+n.x,l.y+n.y]],s=Q().curve(L),_=e.insert("path").attr("d",s(d)).attr("class","path").attr("fill","none"),g=e.insert("g").attr("class","edgeLabel"),k=B(g.node().appendChild(r.labelEl)),o=k.node().firstChild.getBoundingClientRect();k.attr("width",o.width),k.attr("height",o.height),g.attr("transform",`translate(${r.labels[0].x+n.x}, ${r.labels[0].y+n.y})`),ae(_,t,a.type,a.arrowMarkerAbsolute)},H=(e,r)=>{e.forEach(t=>{t.children||(t.children=[]);const a=r.childrenById[t.id];a&&a.forEach(i=>{t.children.push(v[i])}),H(t.children,r)})},de=async function(e,r,t,a){var i;if(!P){const u=(await W(()=>import("./elk.bundled-dba87a86-d5f889f9.js"),["assets/elk.bundled-dba87a86-d5f889f9.js","assets/mermaid.esm.min-7b9a39b0.js","assets/app-fb42e106.js","assets/framework-6ad2459b.js"]).then(b=>b.e)).default;P=new u}a.db.clear(),v={},a.db.setGen("gen-2"),a.parser.parse(e);const n=B("body").append("div").attr("style","height:400px").attr("id","cy");let p={id:"root",layoutOptions:{"elk.hierarchyHandling":"INCLUDE_CHILDREN","org.eclipse.elk.padding":"[top=100, left=100, bottom=110, right=110]","elk.layered.spacing.edgeNodeBetweenLayers":"30","elk.direction":"DOWN"},children:[],edges:[]};switch(y.info("Drawing flowchart using v3 renderer",P),a.db.getDirection()){case"BT":p.layoutOptions["elk.direction"]="UP";break;case"TB":p.layoutOptions["elk.direction"]="DOWN";break;case"LR":p.layoutOptions["elk.direction"]="RIGHT";break;case"RL":p.layoutOptions["elk.direction"]="LEFT";break}const{securityLevel:l,flowchart:w}=O();let d;l==="sandbox"&&(d=B("#i"+r));const s=l==="sandbox"?B(d.nodes()[0].contentDocument.body):B("body"),_=l==="sandbox"?d.nodes()[0].contentDocument:document,g=s.select(`[id="${r}"]`);q(g,["point","circle","cross"],a.type,a.arrowMarkerAbsolute);const k=a.db.getVertices();let o;const h=a.db.getSubGraphs();y.info("Subgraphs - ",h);for(let u=h.length-1;u>=0;u--)o=h[u],a.db.addVertex(o.id,o.title,"group",void 0,o.classes,o.dir);const $=g.insert("g").attr("class","subgraphs"),m=le(a.db);p=te(k,r,s,_,a,m,p);const c=g.insert("g").attr("class","edges edgePath"),E=a.db.getEdges();p=oe(E,a,p,g),Object.keys(v).forEach(u=>{const b=v[u];b.parent||p.children.push(b),m.childrenById[u]!==void 0&&(b.labels=[{text:b.labelText,layoutOptions:{"nodeLabels.placement":"[H_CENTER, V_TOP, INSIDE]"},width:b.labelData.width,height:b.labelData.height}],delete b.x,delete b.y,delete b.width,delete b.height)}),H(p.children,m),y.info("after layout",JSON.stringify(p,null,2));const x=await P.layout(p);G(0,0,x.children,g,$,a,0),y.info("after layout",x),(i=x.edges)==null||i.map(u=>{ne(c,u,u.edgeData,a,m)}),K({},g,w.diagramPadding,w.useMaxWidth),n.remove()},G=(e,r,t,a,i,n,p)=>{t.forEach(function(l){if(l)if(v[l.id].offset={posX:l.x+e,posY:l.y+r,x:e,y:r,depth:p,width:l.width,height:l.height},l.type==="group"){const w=i.insert("g").attr("class","subgraph");w.insert("rect").attr("class","subgraph subgraph-lvl-"+p%5+" node").attr("x",l.x+e).attr("y",l.y+r).attr("width",l.width).attr("height",l.height);const d=w.insert("g").attr("class","label");d.attr("transform",`translate(${l.labels[0].x+e+l.x}, ${l.labels[0].y+r+l.y})`),d.node().appendChild(l.labelData.labelNode),y.info("Id (UGH)= ",l.type,l.labels)}else y.info("Id (UGH)= ",l.id),l.el.attr("transform",`translate(${l.x+e+l.width/2}, ${l.y+r+l.height/2})`)}),t.forEach(function(l){l&&l.type==="group"&&G(e+l.x,r+l.y,l.children,a,i,n,p+1)})},ce={getClasses:ie,draw:de},pe=e=>{let r="";for(let t=0;t<5;t++)r+=`
      .subgraph-lvl-${t} {
        fill: ${e[`surface${t}`]};
        stroke: ${e[`surfacePeer${t}`]};
      }
    `;return r},he=e=>`.label {
    font-family: ${e.fontFamily};
    color: ${e.nodeTextColor||e.textColor};
  }
  .cluster-label text {
    fill: ${e.titleColor};
  }
  .cluster-label span {
    color: ${e.titleColor};
  }

  .label text,span {
    fill: ${e.nodeTextColor||e.textColor};
    color: ${e.nodeTextColor||e.textColor};
  }

  .node rect,
  .node circle,
  .node ellipse,
  .node polygon,
  .node path {
    fill: ${e.mainBkg};
    stroke: ${e.nodeBorder};
    stroke-width: 1px;
  }

  .node .label {
    text-align: center;
  }
  .node.clickable {
    cursor: pointer;
  }

  .arrowheadPath {
    fill: ${e.arrowheadColor};
  }

  .edgePath .path {
    stroke: ${e.lineColor};
    stroke-width: 2.0px;
  }

  .flowchart-link {
    stroke: ${e.lineColor};
    fill: none;
  }

  .edgeLabel {
    background-color: ${e.edgeLabelBackground};
    rect {
      opacity: 0.5;
      background-color: ${e.edgeLabelBackground};
      fill: ${e.edgeLabelBackground};
    }
    text-align: center;
  }

  .cluster rect {
    fill: ${e.clusterBkg};
    stroke: ${e.clusterBorder};
    stroke-width: 1px;
  }

  .cluster text {
    fill: ${e.titleColor};
  }

  .cluster span {
    color: ${e.titleColor};
  }
  /* .cluster div {
    color: ${e.titleColor};
  } */

  div.mermaidTooltip {
    position: absolute;
    text-align: center;
    max-width: 200px;
    padding: 2px;
    font-family: ${e.fontFamily};
    font-size: 12px;
    background: ${e.tertiaryColor};
    border: 1px solid ${e.border2};
    border-radius: 2px;
    pointer-events: none;
    z-index: 100;
  }

  .flowchartTitleText {
    text-anchor: middle;
    font-size: 18px;
    fill: ${e.textColor};
  }
  .subgraph {
    stroke-width:2;
    rx:3;
  }
  // .subgraph-lvl-1 {
  //   fill:#ccc;
  //   // stroke:black;
  // }
  ${pe(e)}
`,ue=he,ve={db:X,renderer:ce,parser:F,styles:ue};export{ve as diagram};
