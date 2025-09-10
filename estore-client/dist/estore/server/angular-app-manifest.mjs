
export default {
  bootstrap: () => import('./main.server.mjs').then(m => m.default),
  inlineCriticalCss: true,
  baseHref: '/',
  locale: undefined,
  routes: [
  {
    "renderMode": 2,
    "redirectTo": "/home",
    "route": "/"
  },
  {
    "renderMode": 2,
    "route": "/home"
  },
  {
    "renderMode": 2,
    "route": "/**"
  }
],
  entryPointToBrowserMapping: undefined,
  assets: {
    'index.csr.html': {size: 4687, hash: '5d9e39675d40d4db65b06b6ed41325c351e20c94fad68b5f5fb8f8a26bcf3dd6', text: () => import('./assets-chunks/index_csr_html.mjs').then(m => m.default)},
    'index.server.html': {size: 1003, hash: 'f115daf06429606e50ac08194c2ef4193bc31d1bf39c0275da982376a7a1f8ed', text: () => import('./assets-chunks/index_server_html.mjs').then(m => m.default)},
    'home/index.html': {size: 58493, hash: '65b390551e5cca71b67a3add1f6d109eec1ab7969eb25ec45ba5bd39e32cdc86', text: () => import('./assets-chunks/home_index_html.mjs').then(m => m.default)},
    'styles-44XY4HYX.css': {size: 16110, hash: 'uMfy/iXwRx4', text: () => import('./assets-chunks/styles-44XY4HYX_css.mjs').then(m => m.default)}
  },
};
