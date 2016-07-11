var path    = require('path');
var node_modules_dir = path.join(__dirname, 'node_modules');

module.exports = function (config) {
  config.set({
    // base path used to resolve all patterns
    basePath: '',

    // frameworks to use
    // available frameworks: https://npmjs.org/browse/keyword/karma-adapter
    frameworks: ['mocha', 'chai'],

    // list of files/patterns to load in the browser
    files: [{ pattern: 'spec.bundle.js', watched: false }],

    // files to exclude
    exclude: [],

    plugins: [
      require("karma-chai"),
      require("karma-chrome-launcher"),
      require("karma-mocha"),
      require("karma-mocha-reporter"),
      require("karma-sourcemap-loader"),
      require("karma-webpack"),
      require("karma-phantomjs-launcher")
    ],

    // preprocess matching files before serving them to the browser
    // available preprocessors: https://npmjs.org/browse/keyword/karma-preprocessor
    preprocessors: { 'spec.bundle.js': ['webpack', 'sourcemap'] },

    webpack: {
      devtool: 'inline-source-map',
      module: {
        loaders: [
          { test: /\.js$/, exclude: [/app\/lib/, /node_modules/], loader: 'ng-annotate!babel' },
          { test: /\.html$/, loader: 'raw' },
          { test: /\.styl$/, loader: 'style!css!stylus' },
          { test: /\.less$/, loader: 'style!css!less' },
          { test: /\.css$/, loader: 'style!css' },
          { test: /\.(png|jpg|gif)$/, loader: "file-loader?name=images/[name].[ext]" },
          //Most libraries use semantic versioning, however kendo has some .ttf's matching 1.0 instead of 1.0.0
          { test: /\.(ttf|eot|svg)(\?v=[0-9]\.[0-9]\.[0-9])?(\?v=[0-9]\.[0-9])?$/, loader: "file-loader?name=fonts/[name].[ext]" },
          { test: /\.woff(2)?(\?v=[0-9]\.[0-9]\.[0-9])?$/, loader: "file-loader?name=fonts/[name].[ext]" },
          //kendo has some wierd ones that look like this KendoUIGlyphs.eot?-wd8xpd
          { test: /\.(ttf|eot|svg)(\?-)(.+)$/, loader: "file-loader?name=fonts/[name].[ext]" },
          { test: /\.woff(2)?(\?-)(.+)$/, loader: "file-loader?name=fonts/[name].[ext]" },
          { test: /lodash\.min\.js$/, loader: 'expose?_' },
          { test: /jquery\.min\.js$/, loader: 'expose?$!expose?jQuery!expose?window.jQuery' },
          { test: /angular\.min\.js/, loader: "expose?angular!exports?window.angular" },
        ]
      },
      resolve: {
        alias: {
          common: path.join(__dirname, 'src/main/app/common'),
          components: path.join(__dirname, 'src/main/app/components'),
          nodeModules: node_modules_dir,

          'jquery': path.join(node_modules_dir, 'jquery/dist/jquery.min.js'),
          'angular': path.join(node_modules_dir, 'angular/angular.min.js'),
          'angular-ui-router': path.join(node_modules_dir, 'angular-ui-router/release/angular-ui-router.min.js'),
          'bootstrap': path.join(node_modules_dir, 'bootstrap/dist/js/bootstrap.min.js'),
        }
      }
    },

    webpackServer: {
      noInfo: true // prevent console spamming when running in Karma!
    },

    // available reporters: https://npmjs.org/browse/keyword/karma-reporter
    reporters: ['mocha'],

    // web server port
    port: 9876,

    // enable colors in the output
    colors: true,

    // level of logging
    // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
    logLevel: config.LOG_INFO,

    // toggle whether to watch files and rerun tests upon incurring changes
    autoWatch: false,

    // start these browsers
    // available browser launchers: https://npmjs.org/browse/keyword/karma-launcher
    browsers: ['PhantomJS'],

    // if true, Karma runs tests once and exits
    singleRun: true,

    phantomjsLauncher: {
      // Have phantomjs exit if a ResourceError is encountered (useful if karma exits without killing phantom)
      exitOnResourceError: true
    }
  });
};
