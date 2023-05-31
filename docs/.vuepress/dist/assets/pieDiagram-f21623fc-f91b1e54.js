import{$ as B,i as yt,a as ft,g as gt,f as dt,l as mt,r as _t,w as vt,K as bt,M as U,s as kt,X as q,c as xt,al as H}from"./mermaid.esm.min-3c52777b.js";import{t as St}from"./init-f9637058-cac434d1.js";import{t as wt}from"./array-2ff2c7a6-ffeda358.js";import{E as R}from"./constant-2fe7eae5-45b4460e.js";import{h as At}from"./arc-f81a5cae-fef8430b.js";import"./app-b0f9f519.js";import"./framework-2d755bf3.js";class rt extends Map{constructor(r,a=Dt){if(super(),Object.defineProperties(this,{_intern:{value:new Map},_key:{value:a}}),r!=null)for(const[c,p]of r)this.set(c,p)}get(r){return super.get(at(this,r))}has(r){return super.has(at(this,r))}set(r,a){return super.set(Et(this,r),a)}delete(r){return super.delete($t(this,r))}}function at({_intern:t,_key:r},a){const c=r(a);return t.has(c)?t.get(c):a}function Et({_intern:t,_key:r},a){const c=r(a);return t.has(c)?t.get(c):(t.set(c,a),a)}function $t({_intern:t,_key:r},a){const c=r(a);return t.has(c)&&(a=t.get(c),t.delete(c)),a}function Dt(t){return t!==null&&typeof t=="object"?t.valueOf():t}const ct=Symbol("implicit");function ot(){var t=new rt,r=[],a=[],c=ct;function p(u){let h=t.get(u);if(h===void 0){if(c!==ct)return c;t.set(u,h=r.push(u)-1)}return a[h%a.length]}return p.domain=function(u){if(!arguments.length)return r.slice();r=[],t=new rt;for(const h of u)t.has(h)||t.set(h,r.push(h)-1);return p},p.range=function(u){return arguments.length?(a=Array.from(u),p):a.slice()},p.unknown=function(u){return arguments.length?(c=u,p):c},p.copy=function(){return ot(r,a).unknown(c)},St.apply(p,arguments),p}function Tt(t,r){return r<t?-1:r>t?1:r>=t?0:NaN}function It(t){return t}function Ot(){var t=It,r=Tt,a=null,c=R(0),p=R(H),u=R(0);function h(o){var y,f=(o=wt(o)).length,A,W,b=0,D=new Array(f),S=new Array(f),w=+c.apply(this,arguments),T=Math.min(H,Math.max(-H,p.apply(this,arguments)-w)),I,k=Math.min(Math.abs(T)/f,u.apply(this,arguments)),E=k*(T<0?-1:1),$;for(y=0;y<f;++y)($=S[D[y]=y]=+t(o[y],y,o))>0&&(b+=$);for(r!=null?D.sort(function(O,d){return r(S[O],S[d])}):a!=null&&D.sort(function(O,d){return a(o[O],o[d])}),y=0,W=b?(T-f*E)/b:0;y<f;++y,w=I)A=D[y],$=S[A],I=w+($>0?$*W:0)+E,S[A]={data:o[A],index:y,value:$,startAngle:w,endAngle:I,padAngle:k};return S}return h.value=function(o){return arguments.length?(t=typeof o=="function"?o:R(+o),h):t},h.sortValues=function(o){return arguments.length?(r=o,a=null,h):r},h.sort=function(o){return arguments.length?(a=o,r=null,h):a},h.startAngle=function(o){return arguments.length?(c=typeof o=="function"?o:R(+o),h):c},h.endAngle=function(o){return arguments.length?(p=typeof o=="function"?o:R(+o),h):p},h.padAngle=function(o){return arguments.length?(u=typeof o=="function"?o:R(+o),h):u},h}var tt=function(){var t=function(d,e,n,i){for(n=n||{},i=d.length;i--;n[d[i]]=e);return n},r=[1,4],a=[1,5],c=[1,6],p=[1,7],u=[1,9],h=[1,11,13,15,17,19,20,26,27,28,29],o=[2,5],y=[1,6,11,13,15,17,19,20,26,27,28,29],f=[26,27,28],A=[2,8],W=[1,18],b=[1,19],D=[1,20],S=[1,21],w=[1,22],T=[1,23],I=[1,28],k=[6,26,27,28,29],E={trace:function(){},yy:{},symbols_:{error:2,start:3,eol:4,directive:5,PIE:6,document:7,showData:8,line:9,statement:10,txt:11,value:12,title:13,title_value:14,acc_title:15,acc_title_value:16,acc_descr:17,acc_descr_value:18,acc_descr_multiline_value:19,section:20,openDirective:21,typeDirective:22,closeDirective:23,":":24,argDirective:25,NEWLINE:26,";":27,EOF:28,open_directive:29,type_directive:30,arg_directive:31,close_directive:32,$accept:0,$end:1},terminals_:{2:"error",6:"PIE",8:"showData",11:"txt",12:"value",13:"title",14:"title_value",15:"acc_title",16:"acc_title_value",17:"acc_descr",18:"acc_descr_value",19:"acc_descr_multiline_value",20:"section",24:":",26:"NEWLINE",27:";",28:"EOF",29:"open_directive",30:"type_directive",31:"arg_directive",32:"close_directive"},productions_:[0,[3,2],[3,2],[3,2],[3,3],[7,0],[7,2],[9,2],[10,0],[10,2],[10,2],[10,2],[10,2],[10,1],[10,1],[10,1],[5,3],[5,5],[4,1],[4,1],[4,1],[21,1],[22,1],[25,1],[23,1]],performAction:function(d,e,n,i,l,s,_){var m=s.length-1;switch(l){case 4:i.setShowData(!0);break;case 7:this.$=s[m-1];break;case 9:i.addSection(s[m-1],i.cleanupValue(s[m]));break;case 10:this.$=s[m].trim(),i.setDiagramTitle(this.$);break;case 11:this.$=s[m].trim(),i.setAccTitle(this.$);break;case 12:case 13:this.$=s[m].trim(),i.setAccDescription(this.$);break;case 14:i.addSection(s[m].substr(8)),this.$=s[m].substr(8);break;case 21:i.parseDirective("%%{","open_directive");break;case 22:i.parseDirective(s[m],"type_directive");break;case 23:s[m]=s[m].trim().replace(/'/g,'"'),i.parseDirective(s[m],"arg_directive");break;case 24:i.parseDirective("}%%","close_directive","pie");break}},table:[{3:1,4:2,5:3,6:r,21:8,26:a,27:c,28:p,29:u},{1:[3]},{3:10,4:2,5:3,6:r,21:8,26:a,27:c,28:p,29:u},{3:11,4:2,5:3,6:r,21:8,26:a,27:c,28:p,29:u},t(h,o,{7:12,8:[1,13]}),t(y,[2,18]),t(y,[2,19]),t(y,[2,20]),{22:14,30:[1,15]},{30:[2,21]},{1:[2,1]},{1:[2,2]},t(f,A,{21:8,9:16,10:17,5:24,1:[2,3],11:W,13:b,15:D,17:S,19:w,20:T,29:u}),t(h,o,{7:25}),{23:26,24:[1,27],32:I},t([24,32],[2,22]),t(h,[2,6]),{4:29,26:a,27:c,28:p},{12:[1,30]},{14:[1,31]},{16:[1,32]},{18:[1,33]},t(f,[2,13]),t(f,[2,14]),t(f,[2,15]),t(f,A,{21:8,9:16,10:17,5:24,1:[2,4],11:W,13:b,15:D,17:S,19:w,20:T,29:u}),t(k,[2,16]),{25:34,31:[1,35]},t(k,[2,24]),t(h,[2,7]),t(f,[2,9]),t(f,[2,10]),t(f,[2,11]),t(f,[2,12]),{23:36,32:I},{32:[2,23]},t(k,[2,17])],defaultActions:{9:[2,21],10:[2,1],11:[2,2],35:[2,23]},parseError:function(d,e){if(e.recoverable)this.trace(d);else{var n=new Error(d);throw n.hash=e,n}},parse:function(d){var e=this,n=[0],i=[],l=[null],s=[],_=this.table,m="",V=0,it=0,lt=2,nt=1,ht=s.slice.call(arguments,1),g=Object.create(this.lexer),C={yy:{}};for(var G in this.yy)Object.prototype.hasOwnProperty.call(this.yy,G)&&(C.yy[G]=this.yy[G]);g.setInput(d,C.yy),C.yy.lexer=g,C.yy.parser=this,typeof g.yylloc>"u"&&(g.yylloc={});var K=g.yylloc;s.push(K);var ut=g.options&&g.options.ranges;typeof C.yy.parseError=="function"?this.parseError=C.yy.parseError:this.parseError=Object.getPrototypeOf(this).parseError;function pt(){var F;return F=i.pop()||g.lex()||nt,typeof F!="number"&&(F instanceof Array&&(i=F,F=i.pop()),F=e.symbols_[F]||F),F}for(var v,P,x,X,j={},Y,M,st,J;;){if(P=n[n.length-1],this.defaultActions[P]?x=this.defaultActions[P]:((v===null||typeof v>"u")&&(v=pt()),x=_[P]&&_[P][v]),typeof x>"u"||!x.length||!x[0]){var Z="";J=[];for(Y in _[P])this.terminals_[Y]&&Y>lt&&J.push("'"+this.terminals_[Y]+"'");g.showPosition?Z="Parse error on line "+(V+1)+`:
`+g.showPosition()+`
Expecting `+J.join(", ")+", got '"+(this.terminals_[v]||v)+"'":Z="Parse error on line "+(V+1)+": Unexpected "+(v==nt?"end of input":"'"+(this.terminals_[v]||v)+"'"),this.parseError(Z,{text:g.match,token:this.terminals_[v]||v,line:g.yylineno,loc:K,expected:J})}if(x[0]instanceof Array&&x.length>1)throw new Error("Parse Error: multiple actions possible at state: "+P+", token: "+v);switch(x[0]){case 1:n.push(v),l.push(g.yytext),s.push(g.yylloc),n.push(x[1]),v=null,it=g.yyleng,m=g.yytext,V=g.yylineno,K=g.yylloc;break;case 2:if(M=this.productions_[x[1]][1],j.$=l[l.length-M],j._$={first_line:s[s.length-(M||1)].first_line,last_line:s[s.length-1].last_line,first_column:s[s.length-(M||1)].first_column,last_column:s[s.length-1].last_column},ut&&(j._$.range=[s[s.length-(M||1)].range[0],s[s.length-1].range[1]]),X=this.performAction.apply(j,[m,it,V,C.yy,x[1],l,s].concat(ht)),typeof X<"u")return X;M&&(n=n.slice(0,-1*M*2),l=l.slice(0,-1*M),s=s.slice(0,-1*M)),n.push(this.productions_[x[1]][0]),l.push(j.$),s.push(j._$),st=_[n[n.length-2]][n[n.length-1]],n.push(st);break;case 3:return!0}}return!0}},$=function(){var d={EOF:1,parseError:function(e,n){if(this.yy.parser)this.yy.parser.parseError(e,n);else throw new Error(e)},setInput:function(e,n){return this.yy=n||this.yy||{},this._input=e,this._more=this._backtrack=this.done=!1,this.yylineno=this.yyleng=0,this.yytext=this.matched=this.match="",this.conditionStack=["INITIAL"],this.yylloc={first_line:1,first_column:0,last_line:1,last_column:0},this.options.ranges&&(this.yylloc.range=[0,0]),this.offset=0,this},input:function(){var e=this._input[0];this.yytext+=e,this.yyleng++,this.offset++,this.match+=e,this.matched+=e;var n=e.match(/(?:\r\n?|\n).*/g);return n?(this.yylineno++,this.yylloc.last_line++):this.yylloc.last_column++,this.options.ranges&&this.yylloc.range[1]++,this._input=this._input.slice(1),e},unput:function(e){var n=e.length,i=e.split(/(?:\r\n?|\n)/g);this._input=e+this._input,this.yytext=this.yytext.substr(0,this.yytext.length-n),this.offset-=n;var l=this.match.split(/(?:\r\n?|\n)/g);this.match=this.match.substr(0,this.match.length-1),this.matched=this.matched.substr(0,this.matched.length-1),i.length-1&&(this.yylineno-=i.length-1);var s=this.yylloc.range;return this.yylloc={first_line:this.yylloc.first_line,last_line:this.yylineno+1,first_column:this.yylloc.first_column,last_column:i?(i.length===l.length?this.yylloc.first_column:0)+l[l.length-i.length].length-i[0].length:this.yylloc.first_column-n},this.options.ranges&&(this.yylloc.range=[s[0],s[0]+this.yyleng-n]),this.yyleng=this.yytext.length,this},more:function(){return this._more=!0,this},reject:function(){if(this.options.backtrack_lexer)this._backtrack=!0;else return this.parseError("Lexical error on line "+(this.yylineno+1)+`. You can only invoke reject() in the lexer when the lexer is of the backtracking persuasion (options.backtrack_lexer = true).
`+this.showPosition(),{text:"",token:null,line:this.yylineno});return this},less:function(e){this.unput(this.match.slice(e))},pastInput:function(){var e=this.matched.substr(0,this.matched.length-this.match.length);return(e.length>20?"...":"")+e.substr(-20).replace(/\n/g,"")},upcomingInput:function(){var e=this.match;return e.length<20&&(e+=this._input.substr(0,20-e.length)),(e.substr(0,20)+(e.length>20?"...":"")).replace(/\n/g,"")},showPosition:function(){var e=this.pastInput(),n=new Array(e.length+1).join("-");return e+this.upcomingInput()+`
`+n+"^"},test_match:function(e,n){var i,l,s;if(this.options.backtrack_lexer&&(s={yylineno:this.yylineno,yylloc:{first_line:this.yylloc.first_line,last_line:this.last_line,first_column:this.yylloc.first_column,last_column:this.yylloc.last_column},yytext:this.yytext,match:this.match,matches:this.matches,matched:this.matched,yyleng:this.yyleng,offset:this.offset,_more:this._more,_input:this._input,yy:this.yy,conditionStack:this.conditionStack.slice(0),done:this.done},this.options.ranges&&(s.yylloc.range=this.yylloc.range.slice(0))),l=e[0].match(/(?:\r\n?|\n).*/g),l&&(this.yylineno+=l.length),this.yylloc={first_line:this.yylloc.last_line,last_line:this.yylineno+1,first_column:this.yylloc.last_column,last_column:l?l[l.length-1].length-l[l.length-1].match(/\r?\n?/)[0].length:this.yylloc.last_column+e[0].length},this.yytext+=e[0],this.match+=e[0],this.matches=e,this.yyleng=this.yytext.length,this.options.ranges&&(this.yylloc.range=[this.offset,this.offset+=this.yyleng]),this._more=!1,this._backtrack=!1,this._input=this._input.slice(e[0].length),this.matched+=e[0],i=this.performAction.call(this,this.yy,this,n,this.conditionStack[this.conditionStack.length-1]),this.done&&this._input&&(this.done=!1),i)return i;if(this._backtrack){for(var _ in s)this[_]=s[_];return!1}return!1},next:function(){if(this.done)return this.EOF;this._input||(this.done=!0);var e,n,i,l;this._more||(this.yytext="",this.match="");for(var s=this._currentRules(),_=0;_<s.length;_++)if(i=this._input.match(this.rules[s[_]]),i&&(!n||i[0].length>n[0].length)){if(n=i,l=_,this.options.backtrack_lexer){if(e=this.test_match(i,s[_]),e!==!1)return e;if(this._backtrack){n=!1;continue}else return!1}else if(!this.options.flex)break}return n?(e=this.test_match(n,s[l]),e!==!1?e:!1):this._input===""?this.EOF:this.parseError("Lexical error on line "+(this.yylineno+1)+`. Unrecognized text.
`+this.showPosition(),{text:"",token:null,line:this.yylineno})},lex:function(){var e=this.next();return e||this.lex()},begin:function(e){this.conditionStack.push(e)},popState:function(){var e=this.conditionStack.length-1;return e>0?this.conditionStack.pop():this.conditionStack[0]},_currentRules:function(){return this.conditionStack.length&&this.conditionStack[this.conditionStack.length-1]?this.conditions[this.conditionStack[this.conditionStack.length-1]].rules:this.conditions.INITIAL.rules},topState:function(e){return e=this.conditionStack.length-1-Math.abs(e||0),e>=0?this.conditionStack[e]:"INITIAL"},pushState:function(e){this.begin(e)},stateStackSize:function(){return this.conditionStack.length},options:{"case-insensitive":!0},performAction:function(e,n,i,l){switch(i){case 0:return this.begin("open_directive"),29;case 1:return this.begin("type_directive"),30;case 2:return this.popState(),this.begin("arg_directive"),24;case 3:return this.popState(),this.popState(),32;case 4:return 31;case 5:break;case 6:break;case 7:return 26;case 8:break;case 9:break;case 10:return this.begin("title"),13;case 11:return this.popState(),"title_value";case 12:return this.begin("acc_title"),15;case 13:return this.popState(),"acc_title_value";case 14:return this.begin("acc_descr"),17;case 15:return this.popState(),"acc_descr_value";case 16:this.begin("acc_descr_multiline");break;case 17:this.popState();break;case 18:return"acc_descr_multiline_value";case 19:this.begin("string");break;case 20:this.popState();break;case 21:return"txt";case 22:return 6;case 23:return 8;case 24:return"value";case 25:return 28}},rules:[/^(?:%%\{)/i,/^(?:((?:(?!\}%%)[^:.])*))/i,/^(?::)/i,/^(?:\}%%)/i,/^(?:((?:(?!\}%%).|\n)*))/i,/^(?:%%(?!\{)[^\n]*)/i,/^(?:[^\}]%%[^\n]*)/i,/^(?:[\n\r]+)/i,/^(?:%%[^\n]*)/i,/^(?:[\s]+)/i,/^(?:title\b)/i,/^(?:(?!\n||)*[^\n]*)/i,/^(?:accTitle\s*:\s*)/i,/^(?:(?!\n||)*[^\n]*)/i,/^(?:accDescr\s*:\s*)/i,/^(?:(?!\n||)*[^\n]*)/i,/^(?:accDescr\s*\{\s*)/i,/^(?:[\}])/i,/^(?:[^\}]*)/i,/^(?:["])/i,/^(?:["])/i,/^(?:[^"]*)/i,/^(?:pie\b)/i,/^(?:showData\b)/i,/^(?::[\s]*[\d]+(?:\.[\d]+)?)/i,/^(?:$)/i],conditions:{acc_descr_multiline:{rules:[17,18],inclusive:!1},acc_descr:{rules:[15],inclusive:!1},acc_title:{rules:[13],inclusive:!1},close_directive:{rules:[],inclusive:!1},arg_directive:{rules:[3,4],inclusive:!1},type_directive:{rules:[2,3],inclusive:!1},open_directive:{rules:[1],inclusive:!1},title:{rules:[11],inclusive:!1},string:{rules:[20,21],inclusive:!1},INITIAL:{rules:[0,5,6,7,8,9,10,12,14,16,19,22,23,24,25],inclusive:!0}}};return d}();E.lexer=$;function O(){this.yy={}}return O.prototype=E,E.Parser=O,new O}();tt.parser=tt;const Mt=tt;let Q={},et=!1;const Ft=function(t,r,a){vt.parseDirective(this,t,r,a)},Lt=function(t,r){t=bt.sanitizeText(t,B()),Q[t]===void 0&&(Q[t]=r,U.debug("Added new section :",t))},Nt=()=>Q,Wt=function(t){et=t},Ct=function(){return et},Pt=function(t){return t.substring(0,1)===":"&&(t=t.substring(1).trim()),Number(t.trim())},Rt=function(){Q={},et=!1,kt()},Vt={parseDirective:Ft,getConfig:()=>B().pie,addSection:Lt,getSections:Nt,cleanupValue:Pt,clear:Rt,setAccTitle:yt,getAccTitle:ft,setDiagramTitle:gt,getDiagramTitle:dt,setShowData:Wt,getShowData:Ct,getAccDescription:mt,setAccDescription:_t},jt=t=>`
  .pieCircle{
    stroke: ${t.pieStrokeColor};
    stroke-width : ${t.pieStrokeWidth};
    opacity : ${t.pieOpacity};
  }
  .pieTitleText {
    text-anchor: middle;
    font-size: ${t.pieTitleTextSize};
    fill: ${t.pieTitleTextColor};
    font-family: ${t.fontFamily};
  }
  .slice {
    font-family: ${t.fontFamily};
    fill: ${t.pieSectionTextColor};
    font-size:${t.pieSectionTextSize};
    // fill: white;
  }
  .legend text {
    fill: ${t.pieLegendTextColor};
    font-family: ${t.fontFamily};
    font-size: ${t.pieLegendTextSize};
  }
`,zt=jt;let L=B(),N;const z=450,Ut=(t,r,a,c)=>{try{L=B(),U.debug(`Rendering info diagram
`+t);const k=B().securityLevel;let E;k==="sandbox"&&(E=q("#i"+r));const $=k==="sandbox"?q(E.nodes()[0].contentDocument.body):q("body"),O=k==="sandbox"?E.nodes()[0].contentDocument:document;c.db.clear(),c.parser.parse(t),U.debug("Parsed info diagram");const d=O.getElementById(r);N=d.parentElement.offsetWidth,N===void 0&&(N=1200),L.useWidth!==void 0&&(N=L.useWidth),L.pie.useWidth!==void 0&&(N=L.pie.useWidth);const e=$.select("#"+r);xt(e,z,N,L.pie.useMaxWidth),d.setAttribute("viewBox","0 0 "+N+" "+z);var p=40,u=18,h=4,o=Math.min(N,z)/2-p,y=e.append("g").attr("transform","translate("+N/2+","+z/2+")"),f=c.db.getSections(),A=0;Object.keys(f).forEach(function(i){A+=f[i]});const n=L.themeVariables;var W=[n.pie1,n.pie2,n.pie3,n.pie4,n.pie5,n.pie6,n.pie7,n.pie8,n.pie9,n.pie10,n.pie11,n.pie12],b=ot().range(W),D=Object.entries(f).map(function(i,l){return{order:l,name:i[0],value:i[1]}}),S=Ot().value(function(i){return i.value}).sort(function(i,l){return i.order-l.order}),w=S(D),T=At().innerRadius(0).outerRadius(o);y.selectAll("mySlices").data(w).enter().append("path").attr("d",T).attr("fill",function(i){return b(i.data.name)}).attr("class","pieCircle"),y.selectAll("mySlices").data(w).enter().append("text").text(function(i){return(i.data.value/A*100).toFixed(0)+"%"}).attr("transform",function(i){return"translate("+T.centroid(i)+")"}).style("text-anchor","middle").attr("class","slice"),y.append("text").text(c.db.getDiagramTitle()).attr("x",0).attr("y",-(z-50)/2).attr("class","pieTitleText");var I=y.selectAll(".legend").data(b.domain()).enter().append("g").attr("class","legend").attr("transform",function(i,l){const s=u+h,_=s*b.domain().length/2,m=12*u,V=l*s-_;return"translate("+m+","+V+")"});I.append("rect").attr("width",u).attr("height",u).style("fill",b).style("stroke",b),I.data(w).append("text").attr("x",u+h).attr("y",u-h).text(function(i){return c.db.getShowData()||L.showData||L.pie.showData?i.data.name+" ["+i.data.value+"]":i.data.name})}catch(k){U.error("Error while rendering info diagram"),U.error(k)}},Bt={draw:Ut},qt={parser:Mt,db:Vt,renderer:Bt,styles:zt};export{qt as diagram};
