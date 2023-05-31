import{_ as nt}from"./app-b0f9f519.js";import{b as yt,$ as R,M as I,X as T,e as ft,aE as $t,aF as mt}from"./mermaid.esm.min-3c52777b.js";import{L as bt}from"./is_dark-18838fe5-a586f1cb.js";import"./framework-2d755bf3.js";var Z=function(){var e=function(D,r,c,h){for(c=c||{},h=D.length;h--;c[D[h]]=r);return c},t=[1,4],n=[1,13],s=[1,12],i=[1,15],o=[1,16],d=[1,20],l=[1,19],y=[6,7,8],f=[1,26],b=[1,24],x=[1,25],$=[6,7,11],P=[1,6,13,15,16,19,22],q=[1,33],J=[1,34],A=[1,6,7,11,13,15,16,19,22],H={trace:function(){},yy:{},symbols_:{error:2,start:3,mindMap:4,spaceLines:5,SPACELINE:6,NL:7,MINDMAP:8,document:9,stop:10,EOF:11,statement:12,SPACELIST:13,node:14,ICON:15,CLASS:16,nodeWithId:17,nodeWithoutId:18,NODE_DSTART:19,NODE_DESCR:20,NODE_DEND:21,NODE_ID:22,$accept:0,$end:1},terminals_:{2:"error",6:"SPACELINE",7:"NL",8:"MINDMAP",11:"EOF",13:"SPACELIST",15:"ICON",16:"CLASS",19:"NODE_DSTART",20:"NODE_DESCR",21:"NODE_DEND",22:"NODE_ID"},productions_:[0,[3,1],[3,2],[5,1],[5,2],[5,2],[4,2],[4,3],[10,1],[10,1],[10,1],[10,2],[10,2],[9,3],[9,2],[12,2],[12,2],[12,2],[12,1],[12,1],[12,1],[12,1],[12,1],[14,1],[14,1],[18,3],[17,1],[17,4]],performAction:function(D,r,c,h,u,a,E){var g=a.length-1;switch(u){case 6:case 7:return h;case 8:h.getLogger().trace("Stop NL ");break;case 9:h.getLogger().trace("Stop EOF ");break;case 11:h.getLogger().trace("Stop NL2 ");break;case 12:h.getLogger().trace("Stop EOF2 ");break;case 15:h.getLogger().info("Node: ",a[g].id),h.addNode(a[g-1].length,a[g].id,a[g].descr,a[g].type);break;case 16:h.getLogger().trace("Icon: ",a[g]),h.decorateNode({icon:a[g]});break;case 17:case 21:h.decorateNode({class:a[g]});break;case 18:h.getLogger().trace("SPACELIST");break;case 19:h.getLogger().trace("Node: ",a[g].id),h.addNode(0,a[g].id,a[g].descr,a[g].type);break;case 20:h.decorateNode({icon:a[g]});break;case 25:h.getLogger().trace("node found ..",a[g-2]),this.$={id:a[g-1],descr:a[g-1],type:h.getType(a[g-2],a[g])};break;case 26:this.$={id:a[g],descr:a[g],type:h.nodeType.DEFAULT};break;case 27:h.getLogger().trace("node found ..",a[g-3]),this.$={id:a[g-3],descr:a[g-1],type:h.getType(a[g-2],a[g])};break}},table:[{3:1,4:2,5:3,6:[1,5],8:t},{1:[3]},{1:[2,1]},{4:6,6:[1,7],7:[1,8],8:t},{6:n,7:[1,10],9:9,12:11,13:s,14:14,15:i,16:o,17:17,18:18,19:d,22:l},e(y,[2,3]),{1:[2,2]},e(y,[2,4]),e(y,[2,5]),{1:[2,6],6:n,12:21,13:s,14:14,15:i,16:o,17:17,18:18,19:d,22:l},{6:n,9:22,12:11,13:s,14:14,15:i,16:o,17:17,18:18,19:d,22:l},{6:f,7:b,10:23,11:x},e($,[2,22],{17:17,18:18,14:27,15:[1,28],16:[1,29],19:d,22:l}),e($,[2,18]),e($,[2,19]),e($,[2,20]),e($,[2,21]),e($,[2,23]),e($,[2,24]),e($,[2,26],{19:[1,30]}),{20:[1,31]},{6:f,7:b,10:32,11:x},{1:[2,7],6:n,12:21,13:s,14:14,15:i,16:o,17:17,18:18,19:d,22:l},e(P,[2,14],{7:q,11:J}),e(A,[2,8]),e(A,[2,9]),e(A,[2,10]),e($,[2,15]),e($,[2,16]),e($,[2,17]),{20:[1,35]},{21:[1,36]},e(P,[2,13],{7:q,11:J}),e(A,[2,11]),e(A,[2,12]),{21:[1,37]},e($,[2,25]),e($,[2,27])],defaultActions:{2:[2,1],6:[2,2]},parseError:function(D,r){if(r.recoverable)this.trace(D);else{var c=new Error(D);throw c.hash=r,c}},parse:function(D){var r=this,c=[0],h=[],u=[null],a=[],E=this.table,g="",U=0,K=0,dt=2,tt=1,gt=a.slice.call(arguments,1),m=Object.create(this.lexer),O={yy:{}};for(var G in this.yy)Object.prototype.hasOwnProperty.call(this.yy,G)&&(O.yy[G]=this.yy[G]);m.setInput(D,O.yy),O.yy.lexer=m,O.yy.parser=this,typeof m.yylloc>"u"&&(m.yylloc={});var X=m.yylloc;a.push(X);var pt=m.options&&m.options.ranges;typeof O.yy.parseError=="function"?this.parseError=O.yy.parseError:this.parseError=Object.getPrototypeOf(this).parseError;function ut(){var w;return w=h.pop()||m.lex()||tt,typeof w!="number"&&(w instanceof Array&&(h=w,w=h.pop()),w=r.symbols_[w]||w),w}for(var _,S,N,Y,v={},B,k,et,F;;){if(S=c[c.length-1],this.defaultActions[S]?N=this.defaultActions[S]:((_===null||typeof _>"u")&&(_=ut()),N=E[S]&&E[S][_]),typeof N>"u"||!N.length||!N[0]){var V="";F=[];for(B in E[S])this.terminals_[B]&&B>dt&&F.push("'"+this.terminals_[B]+"'");m.showPosition?V="Parse error on line "+(U+1)+`:
`+m.showPosition()+`
Expecting `+F.join(", ")+", got '"+(this.terminals_[_]||_)+"'":V="Parse error on line "+(U+1)+": Unexpected "+(_==tt?"end of input":"'"+(this.terminals_[_]||_)+"'"),this.parseError(V,{text:m.match,token:this.terminals_[_]||_,line:m.yylineno,loc:X,expected:F})}if(N[0]instanceof Array&&N.length>1)throw new Error("Parse Error: multiple actions possible at state: "+S+", token: "+_);switch(N[0]){case 1:c.push(_),u.push(m.yytext),a.push(m.yylloc),c.push(N[1]),_=null,K=m.yyleng,g=m.yytext,U=m.yylineno,X=m.yylloc;break;case 2:if(k=this.productions_[N[1]][1],v.$=u[u.length-k],v._$={first_line:a[a.length-(k||1)].first_line,last_line:a[a.length-1].last_line,first_column:a[a.length-(k||1)].first_column,last_column:a[a.length-1].last_column},pt&&(v._$.range=[a[a.length-(k||1)].range[0],a[a.length-1].range[1]]),Y=this.performAction.apply(v,[g,K,U,O.yy,N[1],u,a].concat(gt)),typeof Y<"u")return Y;k&&(c=c.slice(0,-1*k*2),u=u.slice(0,-1*k),a=a.slice(0,-1*k)),c.push(this.productions_[N[1]][0]),u.push(v.$),a.push(v._$),et=E[c[c.length-2]][c[c.length-1]],c.push(et);break;case 3:return!0}}return!0}},lt=function(){var D={EOF:1,parseError:function(r,c){if(this.yy.parser)this.yy.parser.parseError(r,c);else throw new Error(r)},setInput:function(r,c){return this.yy=c||this.yy||{},this._input=r,this._more=this._backtrack=this.done=!1,this.yylineno=this.yyleng=0,this.yytext=this.matched=this.match="",this.conditionStack=["INITIAL"],this.yylloc={first_line:1,first_column:0,last_line:1,last_column:0},this.options.ranges&&(this.yylloc.range=[0,0]),this.offset=0,this},input:function(){var r=this._input[0];this.yytext+=r,this.yyleng++,this.offset++,this.match+=r,this.matched+=r;var c=r.match(/(?:\r\n?|\n).*/g);return c?(this.yylineno++,this.yylloc.last_line++):this.yylloc.last_column++,this.options.ranges&&this.yylloc.range[1]++,this._input=this._input.slice(1),r},unput:function(r){var c=r.length,h=r.split(/(?:\r\n?|\n)/g);this._input=r+this._input,this.yytext=this.yytext.substr(0,this.yytext.length-c),this.offset-=c;var u=this.match.split(/(?:\r\n?|\n)/g);this.match=this.match.substr(0,this.match.length-1),this.matched=this.matched.substr(0,this.matched.length-1),h.length-1&&(this.yylineno-=h.length-1);var a=this.yylloc.range;return this.yylloc={first_line:this.yylloc.first_line,last_line:this.yylineno+1,first_column:this.yylloc.first_column,last_column:h?(h.length===u.length?this.yylloc.first_column:0)+u[u.length-h.length].length-h[0].length:this.yylloc.first_column-c},this.options.ranges&&(this.yylloc.range=[a[0],a[0]+this.yyleng-c]),this.yyleng=this.yytext.length,this},more:function(){return this._more=!0,this},reject:function(){if(this.options.backtrack_lexer)this._backtrack=!0;else return this.parseError("Lexical error on line "+(this.yylineno+1)+`. You can only invoke reject() in the lexer when the lexer is of the backtracking persuasion (options.backtrack_lexer = true).
`+this.showPosition(),{text:"",token:null,line:this.yylineno});return this},less:function(r){this.unput(this.match.slice(r))},pastInput:function(){var r=this.matched.substr(0,this.matched.length-this.match.length);return(r.length>20?"...":"")+r.substr(-20).replace(/\n/g,"")},upcomingInput:function(){var r=this.match;return r.length<20&&(r+=this._input.substr(0,20-r.length)),(r.substr(0,20)+(r.length>20?"...":"")).replace(/\n/g,"")},showPosition:function(){var r=this.pastInput(),c=new Array(r.length+1).join("-");return r+this.upcomingInput()+`
`+c+"^"},test_match:function(r,c){var h,u,a;if(this.options.backtrack_lexer&&(a={yylineno:this.yylineno,yylloc:{first_line:this.yylloc.first_line,last_line:this.last_line,first_column:this.yylloc.first_column,last_column:this.yylloc.last_column},yytext:this.yytext,match:this.match,matches:this.matches,matched:this.matched,yyleng:this.yyleng,offset:this.offset,_more:this._more,_input:this._input,yy:this.yy,conditionStack:this.conditionStack.slice(0),done:this.done},this.options.ranges&&(a.yylloc.range=this.yylloc.range.slice(0))),u=r[0].match(/(?:\r\n?|\n).*/g),u&&(this.yylineno+=u.length),this.yylloc={first_line:this.yylloc.last_line,last_line:this.yylineno+1,first_column:this.yylloc.last_column,last_column:u?u[u.length-1].length-u[u.length-1].match(/\r?\n?/)[0].length:this.yylloc.last_column+r[0].length},this.yytext+=r[0],this.match+=r[0],this.matches=r,this.yyleng=this.yytext.length,this.options.ranges&&(this.yylloc.range=[this.offset,this.offset+=this.yyleng]),this._more=!1,this._backtrack=!1,this._input=this._input.slice(r[0].length),this.matched+=r[0],h=this.performAction.call(this,this.yy,this,c,this.conditionStack[this.conditionStack.length-1]),this.done&&this._input&&(this.done=!1),h)return h;if(this._backtrack){for(var E in a)this[E]=a[E];return!1}return!1},next:function(){if(this.done)return this.EOF;this._input||(this.done=!0);var r,c,h,u;this._more||(this.yytext="",this.match="");for(var a=this._currentRules(),E=0;E<a.length;E++)if(h=this._input.match(this.rules[a[E]]),h&&(!c||h[0].length>c[0].length)){if(c=h,u=E,this.options.backtrack_lexer){if(r=this.test_match(h,a[E]),r!==!1)return r;if(this._backtrack){c=!1;continue}else return!1}else if(!this.options.flex)break}return c?(r=this.test_match(c,a[u]),r!==!1?r:!1):this._input===""?this.EOF:this.parseError("Lexical error on line "+(this.yylineno+1)+`. Unrecognized text.
`+this.showPosition(),{text:"",token:null,line:this.yylineno})},lex:function(){var r=this.next();return r||this.lex()},begin:function(r){this.conditionStack.push(r)},popState:function(){var r=this.conditionStack.length-1;return r>0?this.conditionStack.pop():this.conditionStack[0]},_currentRules:function(){return this.conditionStack.length&&this.conditionStack[this.conditionStack.length-1]?this.conditions[this.conditionStack[this.conditionStack.length-1]].rules:this.conditions.INITIAL.rules},topState:function(r){return r=this.conditionStack.length-1-Math.abs(r||0),r>=0?this.conditionStack[r]:"INITIAL"},pushState:function(r){this.begin(r)},stateStackSize:function(){return this.conditionStack.length},options:{"case-insensitive":!0},performAction:function(r,c,h,u){switch(h){case 0:r.getLogger().trace("Found comment",c.yytext);break;case 1:return 8;case 2:this.begin("CLASS");break;case 3:return this.popState(),16;case 4:this.popState();break;case 5:r.getLogger().trace("Begin icon"),this.begin("ICON");break;case 6:return r.getLogger().trace("SPACELINE"),6;case 7:return 7;case 8:return 15;case 9:r.getLogger().trace("end icon"),this.popState();break;case 10:return r.getLogger().trace("Exploding node"),this.begin("NODE"),19;case 11:return r.getLogger().trace("Cloud"),this.begin("NODE"),19;case 12:return r.getLogger().trace("Explosion Bang"),this.begin("NODE"),19;case 13:return r.getLogger().trace("Cloud Bang"),this.begin("NODE"),19;case 14:return this.begin("NODE"),19;case 15:return this.begin("NODE"),19;case 16:return this.begin("NODE"),19;case 17:return this.begin("NODE"),19;case 18:return 13;case 19:return 22;case 20:return 11;case 21:r.getLogger().trace("Starting NSTR"),this.begin("NSTR");break;case 22:return r.getLogger().trace("description:",c.yytext),"NODE_DESCR";case 23:this.popState();break;case 24:return this.popState(),r.getLogger().trace("node end ))"),"NODE_DEND";case 25:return this.popState(),r.getLogger().trace("node end )"),"NODE_DEND";case 26:return this.popState(),r.getLogger().trace("node end ...",c.yytext),"NODE_DEND";case 27:return this.popState(),r.getLogger().trace("node end (("),"NODE_DEND";case 28:return this.popState(),r.getLogger().trace("node end (-"),"NODE_DEND";case 29:return this.popState(),r.getLogger().trace("node end (-"),"NODE_DEND";case 30:return this.popState(),r.getLogger().trace("node end (("),"NODE_DEND";case 31:return this.popState(),r.getLogger().trace("node end (("),"NODE_DEND";case 32:return r.getLogger().trace("Long description:",c.yytext),20;case 33:return r.getLogger().trace("Long description:",c.yytext),20}},rules:[/^(?:\s*%%.*)/i,/^(?:mindmap\b)/i,/^(?::::)/i,/^(?:.+)/i,/^(?:\n)/i,/^(?:::icon\()/i,/^(?:[\s]+[\n])/i,/^(?:[\n]+)/i,/^(?:[^\)]+)/i,/^(?:\))/i,/^(?:-\))/i,/^(?:\(-)/i,/^(?:\)\))/i,/^(?:\))/i,/^(?:\(\()/i,/^(?:\{\{)/i,/^(?:\()/i,/^(?:\[)/i,/^(?:[\s]+)/i,/^(?:[^\(\[\n\-\)\{\}]+)/i,/^(?:$)/i,/^(?:["])/i,/^(?:[^"]+)/i,/^(?:["])/i,/^(?:[\)]\))/i,/^(?:[\)])/i,/^(?:[\]])/i,/^(?:\}\})/i,/^(?:\(-)/i,/^(?:-\))/i,/^(?:\(\()/i,/^(?:\()/i,/^(?:[^\)\]\(\}]+)/i,/^(?:.+(?!\(\())/i],conditions:{CLASS:{rules:[3,4],inclusive:!1},ICON:{rules:[8,9],inclusive:!1},NSTR:{rules:[22,23],inclusive:!1},NODE:{rules:[21,24,25,26,27,28,29,30,31,32,33],inclusive:!1},INITIAL:{rules:[0,1,2,5,6,7,10,11,12,13,14,15,16,17,18,19,20],inclusive:!0}}};return D}();H.lexer=lt;function z(){this.yy={}}return z.prototype=H,H.Parser=z,new z}();Z.parser=Z;const Et=Z,M=e=>yt(e,R());let L=[],it=0,Q={};const _t=()=>{L=[],it=0,Q={}},xt=function(e){for(let t=L.length-1;t>=0;t--)if(L[t].level<e)return L[t];return null},Nt=()=>L.length>0?L[0]:null,Lt=(e,t,n,s)=>{I.info("addNode",e,t,n,s);const i=R(),o={id:it++,nodeId:M(t),level:e,descr:M(n),type:s,children:[],width:R().mindmap.maxNodeWidth};switch(o.type){case p.ROUNDED_RECT:o.padding=2*i.mindmap.padding;break;case p.RECT:o.padding=2*i.mindmap.padding;break;case p.HEXAGON:o.padding=2*i.mindmap.padding;break;default:o.padding=i.mindmap.padding}const d=xt(e);if(d)d.children.push(o),L.push(o);else if(L.length===0)L.push(o);else{let l=new Error('There can be only one root. No parent could be found for ("'+o.descr+'")');throw l.hash={text:"branch "+name,token:"branch "+name,line:"1",loc:{first_line:1,last_line:1,first_column:1,last_column:1},expected:['"checkout '+name+'"']},l}},p={DEFAULT:0,NO_BORDER:0,ROUNDED_RECT:1,RECT:2,CIRCLE:3,CLOUD:4,BANG:5,HEXAGON:6},Dt=(e,t)=>{switch(I.debug("In get type",e,t),e){case"[":return p.RECT;case"(":return t===")"?p.ROUNDED_RECT:p.CLOUD;case"((":return p.CIRCLE;case")":return p.CLOUD;case"))":return p.BANG;case"{{":return p.HEXAGON;default:return p.DEFAULT}},rt=(e,t)=>{Q[e]=t},kt=e=>{const t=L[L.length-1];e&&e.icon&&(t.icon=M(e.icon)),e&&e.class&&(t.class=M(e.class))},C=e=>{switch(e){case p.DEFAULT:return"no-border";case p.RECT:return"rect";case p.ROUNDED_RECT:return"rounded-rect";case p.CIRCLE:return"circle";case p.CLOUD:return"cloud";case p.BANG:return"bang";case p.HEXAGON:return"hexgon";default:return"no-border"}};let st;const wt=e=>{st=e},Ot=()=>I,St=e=>L[e],W=e=>Q[e],It=Object.freeze(Object.defineProperty({__proto__:null,addNode:Lt,clear:_t,decorateNode:kt,getElementById:W,getLogger:Ot,getMindmap:Nt,getNodeById:St,getType:Dt,nodeType:p,get parseError(){return st},sanitizeText:M,setElementForId:rt,setErrorHandler:wt,type2Str:C},Symbol.toStringTag,{value:"Module"})),at=12;function Ct(e,t){e.each(function(){var n=T(this),s=n.text().split(/(\s+|<br>)/).reverse(),i,o=[],d=1.1,l=n.attr("y"),y=parseFloat(n.attr("dy")),f=n.text(null).append("tspan").attr("x",0).attr("y",l).attr("dy",y+"em");for(let b=0;b<s.length;b++)i=s[s.length-1-b],o.push(i),f.text(o.join(" ").trim()),(f.node().getComputedTextLength()>t||i==="<br>")&&(o.pop(),f.text(o.join(" ").trim()),i==="<br>"?o=[""]:o=[i],f=n.append("tspan").attr("x",0).attr("y",l).attr("dy",d+"em").text(i))})}const vt=function(e,t,n){e.append("path").attr("id","node-"+t.id).attr("class","node-bkg node-"+C(t.type)).attr("d",`M0 ${t.height-5} v${-t.height+2*5} q0,-5 5,-5 h${t.width-2*5} q5,0 5,5 v${t.height-5} H0 Z`),e.append("line").attr("class","node-line-"+n).attr("x1",0).attr("y1",t.height).attr("x2",t.width).attr("y2",t.height)},At=function(e,t){e.append("rect").attr("id","node-"+t.id).attr("class","node-bkg node-"+C(t.type)).attr("height",t.height).attr("width",t.width)},Tt=function(e,t){const n=t.width,s=t.height,i=.15*n,o=.25*n,d=.35*n,l=.2*n;e.append("path").attr("id","node-"+t.id).attr("class","node-bkg node-"+C(t.type)).attr("d",`M0 0 a${i},${i} 0 0,1 ${n*.25},${-1*n*.1}
      a${d},${d} 1 0,1 ${n*.4},${-1*n*.1}
      a${o},${o} 1 0,1 ${n*.35},${1*n*.2}

      a${i},${i} 1 0,1 ${n*.15},${1*s*.35}
      a${l},${l} 1 0,1 ${-1*n*.15},${1*s*.65}

      a${o},${i} 1 0,1 ${-1*n*.25},${n*.15}
      a${d},${d} 1 0,1 ${-1*n*.5},${0}
      a${i},${i} 1 0,1 ${-1*n*.25},${-1*n*.15}

      a${i},${i} 1 0,1 ${-1*n*.1},${-1*s*.35}
      a${l},${l} 1 0,1 ${n*.1},${-1*s*.65}

    H0 V0 Z`)},Rt=function(e,t){const n=t.width,s=t.height,i=.15*n;e.append("path").attr("id","node-"+t.id).attr("class","node-bkg node-"+C(t.type)).attr("d",`M0 0 a${i},${i} 1 0,0 ${n*.25},${-1*s*.1}
      a${i},${i} 1 0,0 ${n*.25},${0}
      a${i},${i} 1 0,0 ${n*.25},${0}
      a${i},${i} 1 0,0 ${n*.25},${1*s*.1}

      a${i},${i} 1 0,0 ${n*.15},${1*s*.33}
      a${i*.8},${i*.8} 1 0,0 ${0},${1*s*.34}
      a${i},${i} 1 0,0 ${-1*n*.15},${1*s*.33}

      a${i},${i} 1 0,0 ${-1*n*.25},${s*.15}
      a${i},${i} 1 0,0 ${-1*n*.25},${0}
      a${i},${i} 1 0,0 ${-1*n*.25},${0}
      a${i},${i} 1 0,0 ${-1*n*.25},${-1*s*.15}

      a${i},${i} 1 0,0 ${-1*n*.1},${-1*s*.33}
      a${i*.8},${i*.8} 1 0,0 ${0},${-1*s*.34}
      a${i},${i} 1 0,0 ${n*.1},${-1*s*.33}

    H0 V0 Z`)},Mt=function(e,t){e.append("circle").attr("id","node-"+t.id).attr("class","node-bkg node-"+C(t.type)).attr("r",t.width/2)};function Pt(e,t,n,s,i){return e.insert("polygon",":first-child").attr("points",s.map(function(o){return o.x+","+o.y}).join(" ")).attr("transform","translate("+(i.width-t)/2+", "+n+")")}const Ut=function(e,t){const n=t.height,s=n/4,i=t.width-t.padding+2*s,o=[{x:s,y:0},{x:i-s,y:0},{x:i,y:-n/2},{x:i-s,y:-n},{x:s,y:-n},{x:0,y:-n/2}];Pt(e,i,n,o,t)},Bt=function(e,t){e.append("rect").attr("id","node-"+t.id).attr("class","node-bkg node-"+C(t.type)).attr("height",t.height).attr("rx",t.padding).attr("ry",t.padding).attr("width",t.width)},Ft=function(e,t,n,s){const i=n%(at-1),o=e.append("g");t.section=i;let d="section-"+i;i<0&&(d+=" section-root"),o.attr("class",(t.class?t.class+" ":"")+"mindmap-node "+d);const l=o.append("g"),y=o.append("g"),f=y.append("text").text(t.descr).attr("dy","1em").attr("alignment-baseline","middle").attr("dominant-baseline","middle").attr("text-anchor","middle").call(Ct,t.width).node().getBBox(),b=s.fontSize.replace?s.fontSize.replace("px",""):s.fontSize;if(t.height=f.height+b*1.1*.5+t.padding,t.width=f.width+2*t.padding,t.icon)if(t.type===p.CIRCLE)t.height+=50,t.width+=50,o.append("foreignObject").attr("height","50px").attr("width",t.width).attr("style","text-align: center;").append("div").attr("class","icon-container").append("i").attr("class","node-icon-"+i+" "+t.icon),y.attr("transform","translate("+t.width/2+", "+(t.height/2-1.5*t.padding)+")");else{t.width+=50;const x=t.height;t.height=Math.max(x,60);const $=Math.abs(t.height-x);o.append("foreignObject").attr("width","60px").attr("height",t.height).attr("style","text-align: center;margin-top:"+$/2+"px;").append("div").attr("class","icon-container").append("i").attr("class","node-icon-"+i+" "+t.icon),y.attr("transform","translate("+(25+t.width/2)+", "+($/2+t.padding/2)+")")}else y.attr("transform","translate("+t.width/2+", "+t.padding/2+")");switch(t.type){case p.DEFAULT:vt(l,t,i);break;case p.ROUNDED_RECT:Bt(l,t);break;case p.RECT:At(l,t);break;case p.CIRCLE:l.attr("transform","translate("+t.width/2+", "+ +t.height/2+")"),Mt(l,t);break;case p.CLOUD:Tt(l,t);break;case p.BANG:Rt(l,t);break;case p.HEXAGON:Ut(l,t);break}return rt(t.id,o),t.height},jt=function(e,t,n,s,i){const o=i%(at-1),d=n.x+n.width/2,l=n.y+n.height/2,y=t.x+t.width/2,f=t.y+t.height/2,b=y>d?d+Math.abs(d-y)/2:d-Math.abs(d-y)/2,x=f>l?l+Math.abs(l-f)/2:l-Math.abs(l-f)/2,$=y>d?Math.abs(d-b)/2+d:-Math.abs(d-b)/2+d,P=f>l?Math.abs(l-x)/2+l:-Math.abs(l-x)/2+l;e.append("path").attr("d",n.direction==="TB"||n.direction==="BT"?`M${d},${l} Q${d},${P} ${b},${x} T${y},${f}`:`M${d},${l} Q${$},${l} ${b},${x} T${y},${f}`).attr("class","edge section-edge-"+o+" edge-depth-"+s)},Ht=function(e){const t=W(e.id),n=e.x||0,s=e.y||0;t.attr("transform","translate("+n+","+s+")")},ot={drawNode:Ft,positionNode:Ht,drawEdge:jt};let j;function ct(e,t,n,s){ot.drawNode(e,t,n,s),t.children&&t.children.forEach((i,o)=>{ct(e,i,n<0?o:n,s)})}function zt(e,t){t.edges().map((n,s)=>{const i=n.data();if(n[0]._private.bodyBounds){const o=n[0]._private.rscratch;I.trace("Edge: ",s,i),e.insert("path").attr("d",`M ${o.startX},${o.startY} L ${o.midX},${o.midY} L${o.endX},${o.endY} `).attr("class","edge section-edge-"+i.section+" edge-depth-"+i.depth)}})}function ht(e,t,n,s){t.add({group:"nodes",data:{id:e.id,labelText:e.descr,height:e.height,width:e.width,level:s,nodeId:e.id,padding:e.padding,type:e.type},position:{x:e.x,y:e.y}}),e.children&&e.children.forEach(i=>{ht(i,t,n,s+1),t.add({group:"edges",data:{id:`${e.id}_${i.id}`,source:e.id,target:i.id,depth:s,section:i.section}})})}async function Gt(e,t){if(!j){j=(await nt(()=>import("./cytoscape.cjs-999c166d-c2f1b958.js"),["assets/cytoscape.cjs-999c166d-c2f1b958.js","assets/mermaid.esm.min-3c52777b.js","assets/app-b0f9f519.js","assets/framework-2d755bf3.js"]).then(s=>s.c)).default;const n=(await nt(()=>import("./cytoscape-cose-bilkent-ce67c900-51e78658.js"),["assets/cytoscape-cose-bilkent-ce67c900-51e78658.js","assets/mermaid.esm.min-3c52777b.js","assets/app-b0f9f519.js","assets/framework-2d755bf3.js"]).then(s=>s.c)).default;j.use(n)}return new Promise(n=>{const s=T("body").append("div").attr("id","cy").attr("style","display:none"),i=j({container:document.getElementById("cy"),style:[{selector:"edge",style:{"curve-style":"bezier"}}]});s.remove(),ht(e,i,t,0),i.nodes().forEach(function(o){o.layoutDimensions=()=>{const d=o.data();return{w:d.width,h:d.height}}}),i.layout({name:"cose-bilkent",quality:"proof",styleEnabled:!1,animate:!1}).run(),i.ready(o=>{I.info("Ready",o),n(i)})})}function Xt(e){e.nodes().map((t,n)=>{const s=t.data();s.x=t.position().x,s.y=t.position().y,ot.positionNode(s);const i=W(s.nodeId);I.info("Id:",n,"Position: (",t.position().x,", ",t.position().y,")",s),i.attr("transform",`translate(${t.position().x-s.width/2}, ${t.position().y-s.height/2})`),i.attr("attr",`apa-${n})`)})}const Yt=async(e,t,n,s)=>{const i=R();s.db.clear(),s.parser.parse(e),I.debug(`Renering info diagram
`+e);const o=R().securityLevel;let d;o==="sandbox"&&(d=T("#i"+t));const l=(o==="sandbox"?T(d.nodes()[0].contentDocument.body):T("body")).select("#"+t);l.append("g");const y=s.db.getMindmap(),f=l.append("g");f.attr("class","mindmap-edges");const b=l.append("g");b.attr("class","mindmap-nodes"),ct(b,y,-1,i);const x=await Gt(y,i);zt(f,x),Xt(x),ft(void 0,l,i.mindmap.padding,i.mindmap.useMaxWidth)},Vt={draw:Yt},Zt=e=>{let t="";for(let n=0;n<e.THEME_COLOR_LIMIT;n++)e["lineColor"+n]=e["lineColor"+n]||e["cScaleInv"+n],bt(e["lineColor"+n])?e["lineColor"+n]=$t(e["lineColor"+n],20):e["lineColor"+n]=mt(e["lineColor"+n],20);for(let n=0;n<e.THEME_COLOR_LIMIT;n++){const s=""+(17-3*n);t+=`
    .section-${n-1} rect, .section-${n-1} path, .section-${n-1} circle, .section-${n-1} polygon, .section-${n-1} path  {
      fill: ${e["cScale"+n]};
    }
    .section-${n-1} text {
     fill: ${e["cScaleLabel"+n]};
    }
    .node-icon-${n-1} {
      font-size: 40px;
      color: ${e["cScaleLabel"+n]};
    }
    .section-edge-${n-1}{
      stroke: ${e["cScale"+n]};
    }
    .edge-depth-${n-1}{
      stroke-width: ${s};
    }
    .section-${n-1} line {
      stroke: ${e["cScaleInv"+n]} ;
      stroke-width: 3;
    }

    .disabled, .disabled circle, .disabled text {
      fill: lightgray;
    }
    .disabled text {
      fill: #efefef;
    }
    `}return t},Qt=e=>`
  .edge {
    stroke-width: 3;
  }
  ${Zt(e)}
  .section-root rect, .section-root path, .section-root circle, .section-root polygon  {
    fill: ${e.git0};
  }
  .section-root text {
    fill: ${e.gitBranchLabel0};
  }
  .icon-container {
    height:100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .edge {
    fill: none;
  }
`,Wt=Qt,ee={db:It,renderer:Vt,parser:Et,styles:Wt};export{ee as diagram};
