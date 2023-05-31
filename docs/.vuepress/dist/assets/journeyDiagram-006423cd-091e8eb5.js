import{$ as D,g as ft,f as gt,i as xt,a as mt,r as kt,l as _t,w as bt,s as vt,X as G,c as $t}from"./mermaid.esm.min-0b2a62c9.js";import{h as it}from"./arc-f81a5cae-5449ea9b.js";import"./app-e0b74b01.js";import"./framework-2d755bf3.js";import"./constant-2fe7eae5-45b4460e.js";var K=function(){var e=function(x,n,o,h){for(o=o||{},h=x.length;h--;o[x[h]]=n);return o},t=[1,2],r=[1,5],i=[6,9,11,17,18,20,22,23,24,26],s=[1,15],l=[1,16],c=[1,17],u=[1,18],y=[1,19],g=[1,20],d=[1,24],_=[4,6,9,11,17,18,20,22,23,24,26],p={trace:function(){},yy:{},symbols_:{error:2,start:3,journey:4,document:5,EOF:6,directive:7,line:8,SPACE:9,statement:10,NEWLINE:11,openDirective:12,typeDirective:13,closeDirective:14,":":15,argDirective:16,title:17,acc_title:18,acc_title_value:19,acc_descr:20,acc_descr_value:21,acc_descr_multiline_value:22,section:23,taskName:24,taskData:25,open_directive:26,type_directive:27,arg_directive:28,close_directive:29,$accept:0,$end:1},terminals_:{2:"error",4:"journey",6:"EOF",9:"SPACE",11:"NEWLINE",15:":",17:"title",18:"acc_title",19:"acc_title_value",20:"acc_descr",21:"acc_descr_value",22:"acc_descr_multiline_value",23:"section",24:"taskName",25:"taskData",26:"open_directive",27:"type_directive",28:"arg_directive",29:"close_directive"},productions_:[0,[3,3],[3,2],[5,0],[5,2],[8,2],[8,1],[8,1],[8,1],[7,4],[7,6],[10,1],[10,2],[10,2],[10,1],[10,1],[10,2],[10,1],[12,1],[13,1],[16,1],[14,1]],performAction:function(x,n,o,h,f,a,$){var m=a.length-1;switch(f){case 1:return a[m-1];case 3:this.$=[];break;case 4:a[m-1].push(a[m]),this.$=a[m-1];break;case 5:case 6:this.$=a[m];break;case 7:case 8:this.$=[];break;case 11:h.setDiagramTitle(a[m].substr(6)),this.$=a[m].substr(6);break;case 12:this.$=a[m].trim(),h.setAccTitle(this.$);break;case 13:case 14:this.$=a[m].trim(),h.setAccDescription(this.$);break;case 15:h.addSection(a[m].substr(8)),this.$=a[m].substr(8);break;case 16:h.addTask(a[m-1],a[m]),this.$="task";break;case 18:h.parseDirective("%%{","open_directive");break;case 19:h.parseDirective(a[m],"type_directive");break;case 20:a[m]=a[m].trim().replace(/'/g,'"'),h.parseDirective(a[m],"arg_directive");break;case 21:h.parseDirective("}%%","close_directive","journey");break}},table:[{3:1,4:t,7:3,12:4,26:r},{1:[3]},e(i,[2,3],{5:6}),{3:7,4:t,7:3,12:4,26:r},{13:8,27:[1,9]},{27:[2,18]},{6:[1,10],7:21,8:11,9:[1,12],10:13,11:[1,14],12:4,17:s,18:l,20:c,22:u,23:y,24:g,26:r},{1:[2,2]},{14:22,15:[1,23],29:d},e([15,29],[2,19]),e(i,[2,8],{1:[2,1]}),e(i,[2,4]),{7:21,10:25,12:4,17:s,18:l,20:c,22:u,23:y,24:g,26:r},e(i,[2,6]),e(i,[2,7]),e(i,[2,11]),{19:[1,26]},{21:[1,27]},e(i,[2,14]),e(i,[2,15]),{25:[1,28]},e(i,[2,17]),{11:[1,29]},{16:30,28:[1,31]},{11:[2,21]},e(i,[2,5]),e(i,[2,12]),e(i,[2,13]),e(i,[2,16]),e(_,[2,9]),{14:32,29:d},{29:[2,20]},{11:[1,33]},e(_,[2,10])],defaultActions:{5:[2,18],7:[2,2],24:[2,21],31:[2,20]},parseError:function(x,n){if(n.recoverable)this.trace(x);else{var o=new Error(x);throw o.hash=n,o}},parse:function(x){var n=this,o=[0],h=[],f=[null],a=[],$=this.table,m="",R=0,U=0,ut=2,tt=1,yt=a.slice.call(arguments,1),k=Object.create(this.lexer),P={yy:{}};for(var X in this.yy)Object.prototype.hasOwnProperty.call(this.yy,X)&&(P.yy[X]=this.yy[X]);k.setInput(x,P.yy),P.yy.lexer=k,P.yy.parser=this,typeof k.yylloc>"u"&&(k.yylloc={});var Y=k.yylloc;a.push(Y);var pt=k.options&&k.options.ranges;typeof P.yy.parseError=="function"?this.parseError=P.yy.parseError:this.parseError=Object.getPrototypeOf(this).parseError;function dt(){var E;return E=h.pop()||k.lex()||tt,typeof E!="number"&&(E instanceof Array&&(h=E,E=h.pop()),E=n.symbols_[E]||E),E}for(var w,j,T,H,I={},B,M,et,z;;){if(j=o[o.length-1],this.defaultActions[j]?T=this.defaultActions[j]:((w===null||typeof w>"u")&&(w=dt()),T=$[j]&&$[j][w]),typeof T>"u"||!T.length||!T[0]){var q="";z=[];for(B in $[j])this.terminals_[B]&&B>ut&&z.push("'"+this.terminals_[B]+"'");k.showPosition?q="Parse error on line "+(R+1)+`:
`+k.showPosition()+`
Expecting `+z.join(", ")+", got '"+(this.terminals_[w]||w)+"'":q="Parse error on line "+(R+1)+": Unexpected "+(w==tt?"end of input":"'"+(this.terminals_[w]||w)+"'"),this.parseError(q,{text:k.match,token:this.terminals_[w]||w,line:k.yylineno,loc:Y,expected:z})}if(T[0]instanceof Array&&T.length>1)throw new Error("Parse Error: multiple actions possible at state: "+j+", token: "+w);switch(T[0]){case 1:o.push(w),f.push(k.yytext),a.push(k.yylloc),o.push(T[1]),w=null,U=k.yyleng,m=k.yytext,R=k.yylineno,Y=k.yylloc;break;case 2:if(M=this.productions_[T[1]][1],I.$=f[f.length-M],I._$={first_line:a[a.length-(M||1)].first_line,last_line:a[a.length-1].last_line,first_column:a[a.length-(M||1)].first_column,last_column:a[a.length-1].last_column},pt&&(I._$.range=[a[a.length-(M||1)].range[0],a[a.length-1].range[1]]),H=this.performAction.apply(I,[m,U,R,P.yy,T[1],f,a].concat(yt)),typeof H<"u")return H;M&&(o=o.slice(0,-1*M*2),f=f.slice(0,-1*M),a=a.slice(0,-1*M)),o.push(this.productions_[T[1]][0]),f.push(I.$),a.push(I._$),et=$[o[o.length-2]][o[o.length-1]],o.push(et);break;case 3:return!0}}return!0}},b=function(){var x={EOF:1,parseError:function(n,o){if(this.yy.parser)this.yy.parser.parseError(n,o);else throw new Error(n)},setInput:function(n,o){return this.yy=o||this.yy||{},this._input=n,this._more=this._backtrack=this.done=!1,this.yylineno=this.yyleng=0,this.yytext=this.matched=this.match="",this.conditionStack=["INITIAL"],this.yylloc={first_line:1,first_column:0,last_line:1,last_column:0},this.options.ranges&&(this.yylloc.range=[0,0]),this.offset=0,this},input:function(){var n=this._input[0];this.yytext+=n,this.yyleng++,this.offset++,this.match+=n,this.matched+=n;var o=n.match(/(?:\r\n?|\n).*/g);return o?(this.yylineno++,this.yylloc.last_line++):this.yylloc.last_column++,this.options.ranges&&this.yylloc.range[1]++,this._input=this._input.slice(1),n},unput:function(n){var o=n.length,h=n.split(/(?:\r\n?|\n)/g);this._input=n+this._input,this.yytext=this.yytext.substr(0,this.yytext.length-o),this.offset-=o;var f=this.match.split(/(?:\r\n?|\n)/g);this.match=this.match.substr(0,this.match.length-1),this.matched=this.matched.substr(0,this.matched.length-1),h.length-1&&(this.yylineno-=h.length-1);var a=this.yylloc.range;return this.yylloc={first_line:this.yylloc.first_line,last_line:this.yylineno+1,first_column:this.yylloc.first_column,last_column:h?(h.length===f.length?this.yylloc.first_column:0)+f[f.length-h.length].length-h[0].length:this.yylloc.first_column-o},this.options.ranges&&(this.yylloc.range=[a[0],a[0]+this.yyleng-o]),this.yyleng=this.yytext.length,this},more:function(){return this._more=!0,this},reject:function(){if(this.options.backtrack_lexer)this._backtrack=!0;else return this.parseError("Lexical error on line "+(this.yylineno+1)+`. You can only invoke reject() in the lexer when the lexer is of the backtracking persuasion (options.backtrack_lexer = true).
`+this.showPosition(),{text:"",token:null,line:this.yylineno});return this},less:function(n){this.unput(this.match.slice(n))},pastInput:function(){var n=this.matched.substr(0,this.matched.length-this.match.length);return(n.length>20?"...":"")+n.substr(-20).replace(/\n/g,"")},upcomingInput:function(){var n=this.match;return n.length<20&&(n+=this._input.substr(0,20-n.length)),(n.substr(0,20)+(n.length>20?"...":"")).replace(/\n/g,"")},showPosition:function(){var n=this.pastInput(),o=new Array(n.length+1).join("-");return n+this.upcomingInput()+`
`+o+"^"},test_match:function(n,o){var h,f,a;if(this.options.backtrack_lexer&&(a={yylineno:this.yylineno,yylloc:{first_line:this.yylloc.first_line,last_line:this.last_line,first_column:this.yylloc.first_column,last_column:this.yylloc.last_column},yytext:this.yytext,match:this.match,matches:this.matches,matched:this.matched,yyleng:this.yyleng,offset:this.offset,_more:this._more,_input:this._input,yy:this.yy,conditionStack:this.conditionStack.slice(0),done:this.done},this.options.ranges&&(a.yylloc.range=this.yylloc.range.slice(0))),f=n[0].match(/(?:\r\n?|\n).*/g),f&&(this.yylineno+=f.length),this.yylloc={first_line:this.yylloc.last_line,last_line:this.yylineno+1,first_column:this.yylloc.last_column,last_column:f?f[f.length-1].length-f[f.length-1].match(/\r?\n?/)[0].length:this.yylloc.last_column+n[0].length},this.yytext+=n[0],this.match+=n[0],this.matches=n,this.yyleng=this.yytext.length,this.options.ranges&&(this.yylloc.range=[this.offset,this.offset+=this.yyleng]),this._more=!1,this._backtrack=!1,this._input=this._input.slice(n[0].length),this.matched+=n[0],h=this.performAction.call(this,this.yy,this,o,this.conditionStack[this.conditionStack.length-1]),this.done&&this._input&&(this.done=!1),h)return h;if(this._backtrack){for(var $ in a)this[$]=a[$];return!1}return!1},next:function(){if(this.done)return this.EOF;this._input||(this.done=!0);var n,o,h,f;this._more||(this.yytext="",this.match="");for(var a=this._currentRules(),$=0;$<a.length;$++)if(h=this._input.match(this.rules[a[$]]),h&&(!o||h[0].length>o[0].length)){if(o=h,f=$,this.options.backtrack_lexer){if(n=this.test_match(h,a[$]),n!==!1)return n;if(this._backtrack){o=!1;continue}else return!1}else if(!this.options.flex)break}return o?(n=this.test_match(o,a[f]),n!==!1?n:!1):this._input===""?this.EOF:this.parseError("Lexical error on line "+(this.yylineno+1)+`. Unrecognized text.
`+this.showPosition(),{text:"",token:null,line:this.yylineno})},lex:function(){var n=this.next();return n||this.lex()},begin:function(n){this.conditionStack.push(n)},popState:function(){var n=this.conditionStack.length-1;return n>0?this.conditionStack.pop():this.conditionStack[0]},_currentRules:function(){return this.conditionStack.length&&this.conditionStack[this.conditionStack.length-1]?this.conditions[this.conditionStack[this.conditionStack.length-1]].rules:this.conditions.INITIAL.rules},topState:function(n){return n=this.conditionStack.length-1-Math.abs(n||0),n>=0?this.conditionStack[n]:"INITIAL"},pushState:function(n){this.begin(n)},stateStackSize:function(){return this.conditionStack.length},options:{"case-insensitive":!0},performAction:function(n,o,h,f){switch(h){case 0:return this.begin("open_directive"),26;case 1:return this.begin("type_directive"),27;case 2:return this.popState(),this.begin("arg_directive"),15;case 3:return this.popState(),this.popState(),29;case 4:return 28;case 5:break;case 6:break;case 7:return 11;case 8:break;case 9:break;case 10:return 4;case 11:return 17;case 12:return this.begin("acc_title"),18;case 13:return this.popState(),"acc_title_value";case 14:return this.begin("acc_descr"),20;case 15:return this.popState(),"acc_descr_value";case 16:this.begin("acc_descr_multiline");break;case 17:this.popState();break;case 18:return"acc_descr_multiline_value";case 19:return 23;case 20:return 24;case 21:return 25;case 22:return 15;case 23:return 6;case 24:return"INVALID"}},rules:[/^(?:%%\{)/i,/^(?:((?:(?!\}%%)[^:.])*))/i,/^(?::)/i,/^(?:\}%%)/i,/^(?:((?:(?!\}%%).|\n)*))/i,/^(?:%(?!\{)[^\n]*)/i,/^(?:[^\}]%%[^\n]*)/i,/^(?:[\n]+)/i,/^(?:\s+)/i,/^(?:#[^\n]*)/i,/^(?:journey\b)/i,/^(?:title\s[^#\n;]+)/i,/^(?:accTitle\s*:\s*)/i,/^(?:(?!\n||)*[^\n]*)/i,/^(?:accDescr\s*:\s*)/i,/^(?:(?!\n||)*[^\n]*)/i,/^(?:accDescr\s*\{\s*)/i,/^(?:[\}])/i,/^(?:[^\}]*)/i,/^(?:section\s[^#:\n;]+)/i,/^(?:[^#:\n;]+)/i,/^(?::[^#\n;]+)/i,/^(?::)/i,/^(?:$)/i,/^(?:.)/i],conditions:{open_directive:{rules:[1],inclusive:!1},type_directive:{rules:[2,3],inclusive:!1},arg_directive:{rules:[3,4],inclusive:!1},acc_descr_multiline:{rules:[17,18],inclusive:!1},acc_descr:{rules:[15],inclusive:!1},acc_title:{rules:[13],inclusive:!1},INITIAL:{rules:[0,5,6,7,8,9,10,11,12,14,16,19,20,21,22,23,24],inclusive:!0}}};return x}();p.lexer=b;function v(){this.yy={}}return v.prototype=p,p.Parser=v,new v}();K.parser=K;const wt=K;let O="";const Q=[],V=[],L=[],Tt=function(e,t,r){bt.parseDirective(this,e,t,r)},St=function(){Q.length=0,V.length=0,O="",L.length=0,vt()},Mt=function(e){O=e,Q.push(e)},Et=function(){return Q},At=function(){let e=rt();const t=100;let r=0;for(;!e&&r<t;)e=rt(),r++;return V.push(...L),V},Pt=function(){const e=[];return V.forEach(t=>{t.people&&e.push(...t.people)}),[...new Set(e)].sort()},jt=function(e,t){const r=t.substr(1).split(":");let i=0,s=[];r.length===1?(i=Number(r[0]),s=[]):(i=Number(r[0]),s=r[1].split(","));const l=s.map(u=>u.trim()),c={section:O,type:O,people:l,task:e,score:i};L.push(c)},Ct=function(e){const t={section:O,type:O,description:e,task:e,classes:[]};V.push(t)},rt=function(){const e=function(r){return L[r].processed};let t=!0;for(const[r,i]of L.entries())e(r),t=t&&i.processed;return t},Dt=function(){return Pt()},st={parseDirective:Tt,getConfig:()=>D().journey,clear:St,setDiagramTitle:ft,getDiagramTitle:gt,setAccTitle:xt,getAccTitle:mt,setAccDescription:kt,getAccDescription:_t,addSection:Mt,getSections:Et,getTasks:At,addTask:jt,addTaskOrg:Ct,getActors:Dt},It=e=>`.label {
    font-family: 'trebuchet ms', verdana, arial, sans-serif;
    font-family: var(--mermaid-font-family);
    color: ${e.textColor};
  }
  .mouth {
    stroke: #666;
  }

  line {
    stroke: ${e.textColor}
  }

  .legend {
    fill: ${e.textColor};
  }

  .label text {
    fill: #333;
  }
  .label {
    color: ${e.textColor}
  }

  .face {
    ${e.faceColor?`fill: ${e.faceColor}`:"fill: #FFF8DC"};
    stroke: #999;
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
    stroke-width: 1.5px;
  }

  .flowchart-link {
    stroke: ${e.lineColor};
    fill: none;
  }

  .edgeLabel {
    background-color: ${e.edgeLabelBackground};
    rect {
      opacity: 0.5;
    }
    text-align: center;
  }

  .cluster rect {
  }

  .cluster text {
    fill: ${e.titleColor};
  }

  div.mermaidTooltip {
    position: absolute;
    text-align: center;
    max-width: 200px;
    padding: 2px;
    font-family: 'trebuchet ms', verdana, arial, sans-serif;
    font-family: var(--mermaid-font-family);
    font-size: 12px;
    background: ${e.tertiaryColor};
    border: 1px solid ${e.border2};
    border-radius: 2px;
    pointer-events: none;
    z-index: 100;
  }

  .task-type-0, .section-type-0  {
    ${e.fillType0?`fill: ${e.fillType0}`:""};
  }
  .task-type-1, .section-type-1  {
    ${e.fillType0?`fill: ${e.fillType1}`:""};
  }
  .task-type-2, .section-type-2  {
    ${e.fillType0?`fill: ${e.fillType2}`:""};
  }
  .task-type-3, .section-type-3  {
    ${e.fillType0?`fill: ${e.fillType3}`:""};
  }
  .task-type-4, .section-type-4  {
    ${e.fillType0?`fill: ${e.fillType4}`:""};
  }
  .task-type-5, .section-type-5  {
    ${e.fillType0?`fill: ${e.fillType5}`:""};
  }
  .task-type-6, .section-type-6  {
    ${e.fillType0?`fill: ${e.fillType6}`:""};
  }
  .task-type-7, .section-type-7  {
    ${e.fillType0?`fill: ${e.fillType7}`:""};
  }

  .actor-0 {
    ${e.actor0?`fill: ${e.actor0}`:""};
  }
  .actor-1 {
    ${e.actor1?`fill: ${e.actor1}`:""};
  }
  .actor-2 {
    ${e.actor2?`fill: ${e.actor2}`:""};
  }
  .actor-3 {
    ${e.actor3?`fill: ${e.actor3}`:""};
  }
  .actor-4 {
    ${e.actor4?`fill: ${e.actor4}`:""};
  }
  .actor-5 {
    ${e.actor5?`fill: ${e.actor5}`:""};
  }
`,Ot=It,F=function(e,t){const r=e.append("rect");return r.attr("x",t.x),r.attr("y",t.y),r.attr("fill",t.fill),r.attr("stroke",t.stroke),r.attr("width",t.width),r.attr("height",t.height),r.attr("rx",t.rx),r.attr("ry",t.ry),t.class!==void 0&&r.attr("class",t.class),r},Vt=function(e,t){const r=e.append("circle").attr("cx",t.cx).attr("cy",t.cy).attr("class","face").attr("r",15).attr("stroke-width",2).attr("overflow","visible"),i=e.append("g");i.append("circle").attr("cx",t.cx-15/3).attr("cy",t.cy-15/3).attr("r",1.5).attr("stroke-width",2).attr("fill","#666").attr("stroke","#666"),i.append("circle").attr("cx",t.cx+15/3).attr("cy",t.cy-15/3).attr("r",1.5).attr("stroke-width",2).attr("fill","#666").attr("stroke","#666");function s(u){const y=it().startAngle(Math.PI/2).endAngle(3*(Math.PI/2)).innerRadius(7.5).outerRadius(6.8181818181818175);u.append("path").attr("class","mouth").attr("d",y).attr("transform","translate("+t.cx+","+(t.cy+2)+")")}function l(u){const y=it().startAngle(3*Math.PI/2).endAngle(5*(Math.PI/2)).innerRadius(7.5).outerRadius(6.8181818181818175);u.append("path").attr("class","mouth").attr("d",y).attr("transform","translate("+t.cx+","+(t.cy+7)+")")}function c(u){u.append("line").attr("class","mouth").attr("stroke",2).attr("x1",t.cx-5).attr("y1",t.cy+7).attr("x2",t.cx+5).attr("y2",t.cy+7).attr("class","mouth").attr("stroke-width","1px").attr("stroke","#666")}return t.score>3?s(i):t.score<3?l(i):c(i),r},ct=function(e,t){const r=e.append("circle");return r.attr("cx",t.cx),r.attr("cy",t.cy),r.attr("class","actor-"+t.pos),r.attr("fill",t.fill),r.attr("stroke",t.stroke),r.attr("r",t.r),r.class!==void 0&&r.attr("class",r.class),t.title!==void 0&&r.append("title").text(t.title),r},lt=function(e,t){const r=t.text.replace(/<br\s*\/?>/gi," "),i=e.append("text");i.attr("x",t.x),i.attr("y",t.y),i.attr("class","legend"),i.style("text-anchor",t.anchor),t.class!==void 0&&i.attr("class",t.class);const s=i.append("tspan");return s.attr("x",t.x+t.textMargin*2),s.text(r),i},Lt=function(e,t){function r(s,l,c,u,y){return s+","+l+" "+(s+c)+","+l+" "+(s+c)+","+(l+u-y)+" "+(s+c-y*1.2)+","+(l+u)+" "+s+","+(l+u)}const i=e.append("polygon");i.attr("points",r(t.x,t.y,50,20,7)),i.attr("class","labelBox"),t.y=t.y+t.labelMargin,t.x=t.x+.5*t.labelMargin,lt(e,t)},Nt=function(e,t,r){const i=e.append("g"),s=Z();s.x=t.x,s.y=t.y,s.fill=t.fill,s.width=r.width,s.height=r.height,s.class="journey-section section-type-"+t.num,s.rx=3,s.ry=3,F(i,s),ht(r)(t.text,i,s.x,s.y,s.width,s.height,{class:"journey-section section-type-"+t.num},r,t.colour)};let nt=-1;const Rt=function(e,t,r){const i=t.x+r.width/2,s=e.append("g");nt++;const l=300+5*30;s.append("line").attr("id","task"+nt).attr("x1",i).attr("y1",t.y).attr("x2",i).attr("y2",l).attr("class","task-line").attr("stroke-width","1px").attr("stroke-dasharray","4 2").attr("stroke","#666"),Vt(s,{cx:i,cy:300+(5-t.score)*30,score:t.score});const c=Z();c.x=t.x,c.y=t.y,c.fill=t.fill,c.width=r.width,c.height=r.height,c.class="task task-type-"+t.num,c.rx=3,c.ry=3,F(s,c);let u=t.x+14;t.people.forEach(y=>{const g=t.actors[y].color,d={cx:u,cy:t.y,r:7,fill:g,stroke:"#000",title:y,pos:t.actors[y].position};ct(s,d),u+=10}),ht(r)(t.task,s,c.x,c.y,c.width,c.height,{class:"task"},r,t.colour)},Bt=function(e,t){F(e,{x:t.startx,y:t.starty,width:t.stopx-t.startx,height:t.stopy-t.starty,fill:t.fill,class:"rect"}).lower()},zt=function(){return{x:0,y:0,fill:void 0,"text-anchor":"start",width:100,height:100,textMargin:0,rx:0,ry:0}},Z=function(){return{x:0,y:0,width:100,anchor:"start",height:100,rx:0,ry:0}},ht=function(){function e(s,l,c,u,y,g,d,_){const p=l.append("text").attr("x",c+y/2).attr("y",u+g/2+5).style("font-color",_).style("text-anchor","middle").text(s);i(p,d)}function t(s,l,c,u,y,g,d,_,p){const{taskFontSize:b,taskFontFamily:v}=_,x=s.split(/<br\s*\/?>/gi);for(let n=0;n<x.length;n++){const o=n*b-b*(x.length-1)/2,h=l.append("text").attr("x",c+y/2).attr("y",u).attr("fill",p).style("text-anchor","middle").style("font-size",b).style("font-family",v);h.append("tspan").attr("x",c+y/2).attr("dy",o).text(x[n]),h.attr("y",u+g/2).attr("dominant-baseline","central").attr("alignment-baseline","central"),i(h,d)}}function r(s,l,c,u,y,g,d,_){const p=l.append("switch"),b=p.append("foreignObject").attr("x",c).attr("y",u).attr("width",y).attr("height",g).attr("position","fixed").append("xhtml:div").style("display","table").style("height","100%").style("width","100%");b.append("div").attr("class","label").style("display","table-cell").style("text-align","center").style("vertical-align","middle").text(s),t(s,p,c,u,y,g,d,_),i(b,d)}function i(s,l){for(const c in l)c in l&&s.attr(c,l[c])}return function(s){return s.textPlacement==="fo"?r:s.textPlacement==="old"?e:t}}(),Ft=function(e){e.append("defs").append("marker").attr("id","arrowhead").attr("refX",5).attr("refY",2).attr("markerWidth",6).attr("markerHeight",4).attr("orient","auto").append("path").attr("d","M 0,0 V 4 L6,2 Z")},N={drawRect:F,drawCircle:ct,drawSection:Nt,drawText:lt,drawLabel:Lt,drawTask:Rt,drawBackgroundRect:Bt,getTextObj:zt,getNoteRect:Z,initGraphics:Ft},Wt=function(e){Object.keys(e).forEach(function(t){W[t]=e[t]})},A={};function Xt(e){const t=D().journey;let r=60;Object.keys(A).forEach(i=>{const s=A[i].color,l={cx:20,cy:r,r:7,fill:s,stroke:"#000",pos:A[i].position};N.drawCircle(e,l);const c={x:40,y:r+7,fill:"#666",text:i,textMargin:t.boxTextMargin|5};N.drawText(e,c),r+=20})}const W=D().journey,C=W.leftMargin,Yt=function(e,t,r,i){const s=D().journey;i.db.clear(),i.parser.parse(e+`
`);const l=D().securityLevel;let c;l==="sandbox"&&(c=G("#i"+t));const u=l==="sandbox"?G(c.nodes()[0].contentDocument.body):G("body");S.init();const y=u.select("#"+t);N.initGraphics(y);const g=i.db.getTasks(),d=i.db.getDiagramTitle(),_=i.db.getActors();for(const o in A)delete A[o];let p=0;_.forEach(o=>{A[o]={color:s.actorColours[p%s.actorColours.length],position:p},p++}),Xt(y),S.insert(0,0,C,Object.keys(A).length*50),Ht(y,g,0);const b=S.getBounds();d&&y.append("text").text(d).attr("x",C).attr("font-size","4ex").attr("font-weight","bold").attr("y",25);const v=b.stopy-b.starty+2*s.diagramMarginY,x=C+b.stopx+2*s.diagramMarginX;$t(y,v,x,s.useMaxWidth),y.append("line").attr("x1",C).attr("y1",s.height*4).attr("x2",x-C-4).attr("y2",s.height*4).attr("stroke-width",4).attr("stroke","black").attr("marker-end","url(#arrowhead)");const n=d?70:0;y.attr("viewBox",`${b.startx} -25 ${x} ${v+n}`),y.attr("preserveAspectRatio","xMinYMin meet"),y.attr("height",v+n+25)},S={data:{startx:void 0,stopx:void 0,starty:void 0,stopy:void 0},verticalPos:0,sequenceItems:[],init:function(){this.sequenceItems=[],this.data={startx:void 0,stopx:void 0,starty:void 0,stopy:void 0},this.verticalPos=0},updateVal:function(e,t,r,i){e[t]===void 0?e[t]=r:e[t]=i(r,e[t])},updateBounds:function(e,t,r,i){const s=D().journey,l=this;let c=0;function u(y){return function(g){c++;const d=l.sequenceItems.length-c+1;l.updateVal(g,"starty",t-d*s.boxMargin,Math.min),l.updateVal(g,"stopy",i+d*s.boxMargin,Math.max),l.updateVal(S.data,"startx",e-d*s.boxMargin,Math.min),l.updateVal(S.data,"stopx",r+d*s.boxMargin,Math.max),y!=="activation"&&(l.updateVal(g,"startx",e-d*s.boxMargin,Math.min),l.updateVal(g,"stopx",r+d*s.boxMargin,Math.max),l.updateVal(S.data,"starty",t-d*s.boxMargin,Math.min),l.updateVal(S.data,"stopy",i+d*s.boxMargin,Math.max))}}this.sequenceItems.forEach(u())},insert:function(e,t,r,i){const s=Math.min(e,r),l=Math.max(e,r),c=Math.min(t,i),u=Math.max(t,i);this.updateVal(S.data,"startx",s,Math.min),this.updateVal(S.data,"starty",c,Math.min),this.updateVal(S.data,"stopx",l,Math.max),this.updateVal(S.data,"stopy",u,Math.max),this.updateBounds(s,c,l,u)},bumpVerticalPos:function(e){this.verticalPos=this.verticalPos+e,this.data.stopy=this.verticalPos},getVerticalPos:function(){return this.verticalPos},getBounds:function(){return this.data}},J=W.sectionFills,at=W.sectionColours,Ht=function(e,t,r){const i=D().journey;let s="";const l=i.height*2+i.diagramMarginY,c=r+l;let u=0,y="#CCC",g="black",d=0;for(const[_,p]of t.entries()){if(s!==p.section){y=J[u%J.length],d=u%J.length,g=at[u%at.length];const v={x:_*i.taskMargin+_*i.width+C,y:50,text:p.section,fill:y,num:d,colour:g};N.drawSection(e,v,i),s=p.section,u++}const b=p.people.reduce((v,x)=>(A[x]&&(v[x]=A[x]),v),{});p.x=_*i.taskMargin+_*i.width+C,p.y=c,p.width=i.diagramMarginX,p.height=i.diagramMarginY,p.colour=g,p.fill=y,p.num=d,p.actors=b,N.drawTask(e,p,i),S.insert(p.x,p.y,p.x+p.width+i.taskMargin,300+5*30)}},ot={setConf:Wt,draw:Yt},Zt={parser:wt,db:st,renderer:ot,styles:Ot,init:e=>{ot.setConf(e.journey),st.clear()}};export{Zt as diagram};
